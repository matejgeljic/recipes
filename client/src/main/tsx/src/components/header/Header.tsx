import { browserRouter } from "../../router.tsx";
import logo from '../../assets/logo.png'

export const Header = () => {
    const handleNavigateToHome = () => {
        void browserRouter.navigate('/');
    }

    return (
        <header className={'bg-black flex justify-between items-center px-4 pt-4 sticky top-0 z-50'}>
            <img className={'cursor-pointer'} src={logo} alt={'cook book logo'} onClick={handleNavigateToHome} />
            <span className={'text-white'}>login</span>
        </header>
    )
}