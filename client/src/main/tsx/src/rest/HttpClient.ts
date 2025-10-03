interface RequestOptions extends RequestInit {
  headers?: Record<string, string>;
  withAuth?: boolean; // Flag to determine if request should include auth
}

export interface ApiResponse<T = any> {
  data: T;
  statusCode: number;
  timestamp: string;
}

// Environment variables
const AUTH_HEADER = "Authorization";
const TOKEN_TYPE = "Bearer";

class HttpClient {
  /**
   * Get authentication token from cookies
   */
  private getAuthToken(): string | null {
    if (typeof document === "undefined") return null;

    // Extract token from cookies
    const match = document.cookie.match(new RegExp("(^| )token=([^;]+)"));
    return match ? match[2] : null;
  }

  /**
   * Core request method that handles all HTTP requests
   */
  async request<T = any>(
    endpoint: string,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    const { withAuth = true, ...requestOptions } = options;

    // Default headers
    const headers: HeadersInit = {
      "Content-Type": "application/json",
      ...options.headers,
    };

    // Add auth token if available and required
    if (withAuth) {
      const token = this.getAuthToken();
      if (token) {
        headers[AUTH_HEADER] = `${TOKEN_TYPE} ${token}`;
      }
    }

    const config = {
      ...requestOptions,
      headers,
    };

    try {
      const response = await fetch(endpoint, config);

      // Handle authentication errors
      if (response.status === 401) {
        // Clear token cookie
        document.cookie =
          "token=; expires=Thu, 01 Jan 2000 00:00:00 UTC; path=/; SameSite=Strict";

        throw new Error("Authentication required. Please sign in.");
      }

      // Parse response based on content type
      const contentType = response.headers.get("content-type");
      let data: any;

      if (contentType?.includes("application/json")) {
        data = await response.json();
      } else {
        const text = await response.text();
        data = {
          data: text,
          statusCode: response.status,
          timestamp: new Date().toISOString(),
        };
      }

      // Check for HTTP errors
      if (!response.ok) {
        const errorMessage =
          data?.message || `Error ${response.status}: ${response.statusText}`;
        throw new Error(errorMessage);
      }

      return data;
    } catch (error) {
      console.error("API request error:", error);
      throw error;
    }
  }

  // Convenience methods for HTTP verbs

  get<T = any>(
    endpoint: string,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, { ...options, method: "GET" });
  }

  post<T = any>(
    endpoint: string,
    body?: any,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, {
      ...options,
      method: "POST",
      body: body ? JSON.stringify(body) : undefined,
    });
  }

  put<T = any>(
    endpoint: string,
    body?: any,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, {
      ...options,
      method: "PUT",
      body: body ? JSON.stringify(body) : undefined,
    });
  }

  patch<T = any>(
    endpoint: string,
    body?: any,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, {
      ...options,
      method: "PATCH",
      body: body ? JSON.stringify(body) : undefined,
    });
  }

  delete<T = any>(
    endpoint: string,
    options: RequestOptions = {},
  ): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, { ...options, method: "DELETE" });
  }
}

// Singleton instance
export const httpClient = new HttpClient();
