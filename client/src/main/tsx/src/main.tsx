import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import {browserRouter} from "./router.ts";
import {AuthProvider} from "react-oidc-context";
import {RouterProvider} from "react-router";

const router = browserRouter;

const oidConfig = {
    authority: "http://localhost:9090/realms/recipes-sharing-platform",
    client_id: "recipes-sharing-platform-app",
    redirect_uri: window.location.origin,
    response_type: "code",
    scope: "openid profile email",
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <AuthProvider {...oidConfig}>
          <RouterProvider router={router} />
      </AuthProvider>
  </StrictMode>,
)
