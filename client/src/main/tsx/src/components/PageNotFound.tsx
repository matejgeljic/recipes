import PageNotFoundImage from '../assets/pageNotFound.png'
import {Text} from "./common/text/Text.tsx";
const PageNotFound = () => {
    return (
        <div className={'justify-center items-center h-screen flex flex-col'}>
            <img src={PageNotFoundImage} alt="Page Not Found" className="w-150" />
            <Text tag={'span'}>Page not found</Text>
        </div>
    );
}

export default PageNotFound;