import {RouterProvider} from "react-router";
import {browserRouter} from "./router.ts";
import {useMemo} from "react";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {AuthProvider} from "react-oidc-context";

const oidConfig = {
    authority: "http://localhost:9090/realms/recipes-sharing-platform",
    client_id: "recipes-sharing-platform-app",
    redirect_uri: window.location.origin,
    response_type: "code",
    scope: "openid profile email",
}


export const App = () => {
    const queryClient = useMemo(() => new QueryClient(), []);

    return (
        <AuthProvider {...oidConfig}>
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={browserRouter} />
        </QueryClientProvider>
        </AuthProvider>
    )
}