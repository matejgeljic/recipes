import { RouterProvider } from "react-router";
import { browserRouter } from "./router.tsx";
import {Suspense, useMemo } from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { AuthProvider } from "react-oidc-context";
import {Text} from "./components/common/text/Text.tsx";
import {ErrorBoundary} from "react-error-boundary";
import GeneralApplicationError from "./components/GeneralApplicationError.tsx";
import {Footer} from "./components/footer/Footer.tsx";
import {Header} from "./components/header/Header.tsx";

const oidConfig = {
  authority: "http://localhost:9090/realms/recipes-sharing-platform",
  client_id: "recipes-sharing-platform-app",
  redirect_uri: window.location.origin,
  response_type: "code",
  scope: "openid profile email",
};

export const App = () => {
  const queryClient = useMemo(() => new QueryClient(), []);

  return (
    <AuthProvider {...oidConfig}>
      <QueryClientProvider client={queryClient}>
          <ErrorBoundary fallback={<GeneralApplicationError />}>
          <Suspense fallback={<Text>Loading...</Text>}>
              <div className={'min-h-screen flex flex-col'}>
                  <Header />
                  <main className={'flex-1'}>
                      <RouterProvider router={browserRouter} />
                  </main>
                  <Footer />
              </div>
          </Suspense>
          </ErrorBoundary>
      </QueryClientProvider>
    </AuthProvider>
  );
};