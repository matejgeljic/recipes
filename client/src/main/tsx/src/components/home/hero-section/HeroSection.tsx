import {Heading} from "../../common/heading/Heading.tsx";
import {HeroSectionSearch} from "./HeroSectionSearch.tsx";

export const HeroSection = () => {
    return (
        <section aria-label={'hero-heading'} className={'relative h-[70vh] flex flex-col justify-center items-center gap-6'}>
            <img src={'https://picsum.photos/200'} alt={'hero-image'} className={'absolute inset-0 -z-20 h-full w-full object-cover'}/>
            <div aria-hidden={true} className={'absolute inset-0 -z-10 bg-black/70'} />
            <Heading tag={'h1'} color={'white'}>Recipes Sharing Platform</Heading>
            <HeroSectionSearch />
        </section>
    )
}

