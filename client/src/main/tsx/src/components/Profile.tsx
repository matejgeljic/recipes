import {ProtectedRoute} from "./ProtectedRoute.tsx";

export const Profile = () => {
    return <ProtectedRoute><h1>Profile</h1></ProtectedRoute>
}