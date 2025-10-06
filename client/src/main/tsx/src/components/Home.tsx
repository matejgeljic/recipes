import { useAuth } from "react-oidc-context";
import { Link } from "react-router";
import { useRecipesLoader } from "../useRecipes.ts";
import { Button } from "./common/button/Button.tsx";
import { UserPlus } from "lucide-react";
import { CardList } from "./common/card-list/CardList.tsx";
import { Card, type CardProps } from "./common/card/Card.tsx";

export const Home = () => {
  const { isAuthenticated, signinRedirect, signoutRedirect, isLoading } =
    useAuth();
  const recipes = useRecipesLoader();

  const { data } = recipes;

  if (isLoading) {
    return <h1>loading...</h1>;
  }

  console.log(data?.data.content);

  const cardData = [
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
    {
      imageSrc: "https://picsum.photos/200",
      imageAlt: "image",
      footer: <FooterComponent />,
    },
  ];
  const cardList = cardData.map((card, index) => (
    <Card
      key={index}
      imageSrc={card.imageSrc}
      imageAlt={card.imageAlt}
      footer={card.footer}
    />
  ));

  const cardData2: CardProps[] = [];
  const cardList2 = cardData2.map((card, index) => (
    <Card
      key={index}
      imageSrc={card.imageSrc}
      imageAlt={card.imageAlt}
      footer={card.footer}
    />
  ));

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

      <CardList
        additionalClasses={"p-10"}
        title="Recipes"
        columns={4}
        gap="gap-6"
      >
        {cardList}
      </CardList>
      <CardList title={"Empty"}>{cardList2}</CardList>
    </>
  );
};
const FooterComponent = () => (
  <div className="flex flex-col">
    <span className="font-medium text-gray-900">Delicious Pasta</span>
    <span className="text-sm text-gray-500">A classic Italian dish</span>
  </div>
);
