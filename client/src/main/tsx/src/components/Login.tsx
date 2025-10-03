import { useEffect } from "react";
import { useAuth } from "react-oidc-context";

export const Login = () => {
  const { isLoading, isAuthenticated, signinRedirect } = useAuth();

  useEffect(() => {
    if (isLoading) {
      return;
    }
    if (!isAuthenticated) {
      signinRedirect();
    }
  }, [isLoading, isAuthenticated, signinRedirect]);

  return <div>Redirecting to login...</div>;
};
