import {useSearchParams} from "react-router";
import React, {useState} from "react";
import {Heading} from "../common/heading/Heading.tsx";
import {Input} from "../common/input/Input.tsx";
import {RadioGroup} from "../common/radio-groupe/RadioGroup.tsx";
import {Button} from "../common/button/Button.tsx";
import Page from "../page/Page.tsx";

const RecipesList = () => {
    const [searchParams, setSearchParams] = useSearchParams();

    const initialSearch = searchParams.get("search") ?? "";
    const initialDishType = searchParams.get("dishType") ?? "";

    const [searchInput, setSearchInput] = useState(initialSearch);
    const [dishTypeInput, setDishTypeInput] = useState(initialDishType);

    const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchInput(e.target.value);
    };

    const handleDishTypeChange = (value: string) => {
        setDishTypeInput(value);
    };

    const applyFilters = () => {
        const params = new URLSearchParams();

        if (searchInput.trim()) params.set("search", searchInput.trim());
        if (dishTypeInput) params.set("dishType", dishTypeInput);

        setSearchParams(params);
    };

    const handleEnterKey = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if(e.key === 'Enter') {
            applyFilters();
        }
    }

    const dishTypeOptions = [
        { label: "Main dish", value: "main" },
        { label: "Dessert", value: "dessert" },
        { label: "Soup", value: "soup" },
    ];

    return (
        <Page title={'Browse Recipes'}>
            <Heading tag={"h2"}>Recipes List</Heading>
            <Input label={'Search for'} value={searchInput} onChange={handleSearchChange} onKeyDown={handleEnterKey} />
            <RadioGroup title={'Dish Type'} options={dishTypeOptions} name={'dish-type-filter'} value={dishTypeInput} onChange={handleDishTypeChange} />
            <Button title={'Filter'} onClick={applyFilters} />
        </Page>
    )
}

export default RecipesList;