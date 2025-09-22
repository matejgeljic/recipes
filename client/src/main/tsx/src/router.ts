import {createBrowserRouter} from "react-router";
import {Home} from "./components/Home.tsx";
import {Profile} from "./components/Profile.tsx";
import {Login} from "./components/Login.tsx";

export const browserRouter = createBrowserRouter([
    {
        path: '/',
        Component: Home
    },
    {
        path: '/profile',
        Component: Profile
    },
    {
        path: '/login',
        Component: Login
    }
])