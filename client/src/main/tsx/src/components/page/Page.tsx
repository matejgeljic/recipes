import {type PropsWithChildren, useEffect} from "react";

const Page = ({title, children, additionalClasses}: PropsWithChildren<{title: string, additionalClasses?: string}>) => {
    useEffect(() => {
        document.title = `${title} | CookBook`;
    }, [title]);

    const classNames = [
        'px-16',
        'py-8',
        additionalClasses
    ]
        .filter(Boolean)
        .join(' ')

    return (
        <div className={classNames}>
            {children}
        </div>
    )
}

export default Page;