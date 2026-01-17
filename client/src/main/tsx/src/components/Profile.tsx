import { ProtectedRoute } from "./ProtectedRoute.tsx";

const Profile = () => {
  return (
    <ProtectedRoute>
      <h1>Profile</h1>
    </ProtectedRoute>
  );
};

export default Profile;