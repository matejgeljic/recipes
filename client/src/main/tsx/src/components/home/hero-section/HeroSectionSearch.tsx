import {Search} from "lucide-react";
import React, {useRef, useState} from "react";

export const HeroSectionSearch = () => {
    const [query, setQuery] = useState('')
    const inputRef = useRef<HTMLInputElement>(null);

    const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setQuery(e.target.value)
    }

    const handleSearchSubmit = () => {
        // Implement search functionality here
        console.log('Searching for:', query)
    }

    const handleKeyPressDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if (e.key === 'Enter') {
            handleSearchSubmit()
        }

        if (e.key === 'Escape') {
            inputRef.current?.blur();
        }
    }

    return (
        <div className="relative w-[50%]">
            <input
                id={'hero-search-input'}
                type={'text'}
                value={query}
                ref={inputRef}
                onChange={handleSearchChange}
                onKeyDown={handleKeyPressDown}
                placeholder={'Search recipes...'}
                required={false}
                autoComplete={'off'}
                aria-required={false}
                aria-label={'Search recipes'}
                aria-describedby={'Search recipes input'}
                data-testid={'hero-search-input'}
                className={[
                    "w-full",
                    "block w-full rounded-md",
                    "px-3 py-2",
                    "bg-grey/30",
                    "outline-none",
                    "text-white",
                    "placeholder-white",
                    "border",
                    "border-white",
                    "rounded-xl",
                    "focus:ring-2 ",
                    "focus:ring-black",
                    "focus:outline-none",
                ].join(" ")}
            />
            <Search
                aria-hidden="true"
                role={'button'}
                className="absolute right-4 top-1/2 -translate-y-1/2 text-white cursor-pointer"
                size={20}
                onClick={handleSearchSubmit}
            />
        </div>
    )
}