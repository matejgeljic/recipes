import { useAuth } from "react-oidc-context";
import { Link } from "react-router";
import { useRecipesLoader } from "../useRecipes.ts";
import { Button } from "./common/button/Button.tsx";
import { UserPlus } from "lucide-react";

export const Home = () => {
  const { isAuthenticated, signinRedirect, signoutRedirect, isLoading } =
    useAuth();
  const recipes = useRecipesLoader();

  const { data } = recipes;

  if (isLoading) {
    return <h1>loading...</h1>;
  }

  console.log(data?.data.content);

  return (
    <>
      <h1>Home</h1>
      {!isAuthenticated ? (
        <button onClick={() => signinRedirect()}>login</button>
      ) : (
        <button
          onClick={() =>
            signoutRedirect({
              post_logout_redirect_uri: window.location.origin,
            })
          }
        >
          logout
        </button>
      )}

      <Link to={`/profile`}>Profile</Link>
      <Button
        title={"My Button"}
        variant={"blue"}
        icon={UserPlus}
        isLoading={false}
        type={"button"}
        isDisabled={false}
        onClick={() => alert("clicked")}
      />
    </>
  );
};
