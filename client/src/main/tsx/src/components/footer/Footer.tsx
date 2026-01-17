import {Text} from "../common/text/Text.tsx";
import Pepper from '../../assets/pepper.png'
import Broccoli from '../../assets/broccoli.png'
import Carrot from '../../assets/carrot.png'
import Lemon from '../../assets/lemon.png'
import Pumpkin from '../../assets/pumpkin.png'
import Tomato from '../../assets/tomato.png'

export const Footer = () => {
    const footerHalfIconClass = 'translate-y-1/2 sm:translate-y-[45%] md:translate-y-[40%]';
  return (
    <footer className={'bg-base pt-8 px-16'}>
        <Text weight={'bold'} additionalClasses={'text-[48px]'}>About us</Text>
        <Text tag={'span'}>Our mission at CookBook is to make everyday cooking fun, because we believe that cooking is key to a happier and healthier life for people, communities and the planet. We empower homecooks all over the world to help each other by sharing recipes and cooking tips.</Text>
        <div className={'relative h-16 sm:h-20 md:h-28 lg:h-32 overflow-hidden flex gap-4'}>
            <img className={footerHalfIconClass} src={Pumpkin} alt={'pumpkin icon'} />
            <img className={footerHalfIconClass} src={Broccoli} alt={'broccoli icon'} />
            <img className={footerHalfIconClass} src={Lemon} alt={'lemon icon'} />
            <img className={footerHalfIconClass} src={Tomato} alt={'tomato icon'} />
            <img className={footerHalfIconClass} src={Pepper} alt={'pepper icon'} />
            <img className={footerHalfIconClass} src={Carrot} alt={'carrot icon'} />
        </div>
    </footer>
    );
};