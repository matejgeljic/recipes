import { createBrowserRouter } from "react-router";
import React from "react";

const Home = React.lazy(() => import("./components/home/Home.tsx"));
const Profile = React.lazy(() => import("./components/Profile.tsx"));
const Login = React.lazy(() => import("./components/Login.tsx"));
const RecipeDetails = React.lazy(() => import("./components/recipe-details/RecipeDetails.tsx"));
const NotFound = React.lazy(() => import("./components/PageNotFound"));
const GeneralApplicationError = React.lazy(() => import("./components/GeneralApplicationError.tsx"));
const RecipeList = React.lazy(() => import("./components/recipes-list/RecipesList.tsx"));

export const browserRouter = createBrowserRouter([
    {
        errorElement: <GeneralApplicationError />,
        children: [
            {
                path: "/",
                Component: Home,
            },
            {
                path: "/profile",
                Component: Profile,
            },
            {
                path: "/login",
                Component: Login,
            },
            {
                path: '/recipes',
                Component: RecipeList,
            },
            {
                path: '/recipes/:id',
                Component: RecipeDetails,
            },
            {
                path: '*',
                Component: NotFound
            }
        ]
    }
]);
