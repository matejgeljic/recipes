import { useAuth } from "react-oidc-context";
import { Link } from "react-router";
import { Button } from "../common/button/Button.tsx";
import { UserPlus } from "lucide-react";
import { CardList } from "../common/card-list/CardList.tsx";
import { Card } from "../common/card/Card.tsx";
import {HeroSection} from "./hero-section/HeroSection.tsx";
import {Text} from "../common/text/Text.tsx";
import Page from "../page/Page.tsx";

const Home = () => {
  const { isAuthenticated, signinRedirect, signoutRedirect } =
    useAuth();

  const cardData = [
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
        url: "/recipes?dishType=main",
      footer: <Text align={"center"} size={'large'}>Main Dishes</Text>,
    },    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
          url: "/recipes?dishType=salad",
      footer: <Text align={"center"} size={'large'}>Salads</Text>,
    },{
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
          url: "/recipes?dishType=drink",
      footer: <Text align={"center"} size={'large'}>Drinks</Text>,
    },{
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
            url: "/recipes?dishType=dessert",
      footer: <Text align={"center"} size={'large'}>Desserts</Text>,
    },
  ];
  const cardList = cardData.map((card) => (
   <Link to={card.url}>
       <Card
           imageSrc={card.imageSrc}
           imageAlt={card.imageAlt}
           footer={card.footer}
       />
   </Link>
  ));

  return (
    <>
        <HeroSection />
        <Page title={'Home'}>
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

      <CardList
        columns={4}
        gap="gap-6"
      >
        {cardList}
      </CardList>
        </Page>
    </>
  );
};

export default Home;