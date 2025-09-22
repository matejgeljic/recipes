import {useAuth} from "react-oidc-context";
import {Link} from "react-router";

export const Home = () => {
    const { isAuthenticated, signinRedirect, signoutRedirect, isLoading } = useAuth();

    if(isLoading) {
        return <h1>loading...</h1>
    }

    return (
        <>
            <h1>Home</h1>
            {!isAuthenticated ? <button onClick={() => signinRedirect()}>login</button> : <button onClick={() => signoutRedirect({post_logout_redirect_uri: window.location.origin})}>logout</button>}

            <Link to={`/profile`}>Profile</Link>
        </>
    )
}