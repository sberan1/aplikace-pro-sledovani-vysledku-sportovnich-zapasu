import React, {FormEvent, useState, useEffect} from 'react';
//import '../../pages/HomePagePackage/Navbar.css';
import '../../App.css';
import styles from './SearchBar.module.css';
import {InputText} from "primereact/inputtext";

const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [results, setResults] = useState<any[]>([]);

    const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchTerm(event.target.value);
    };

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();
        console.log('Submitted search term:', searchTerm);
    };

    useEffect(() => {
        if (searchTerm !== '') {
            fetch(`/api/search?term=${encodeURIComponent(searchTerm)}`)
                .then(response => response.json())
                .then(data => setResults(data.results));
        } else {
            setResults([]);
        }
    }, [searchTerm]);

    return (
        <span className={`flex`}>
            <div className={``}>
                <form onSubmit={handleSubmit} className={`flex ${styles.divec}`}>
                    <span className={`${styles.lupa} flex items-center pl-5`}>
                        <svg width="21" height="21" viewBox="0 0 21 21" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M19.7167 21L12.0458 13.3292C11.4625 13.8347 10.7823 14.2285 10.0054 14.5104C9.22838 14.7924 8.40159 14.9333 7.525 14.9333C5.42185 14.9333 3.6419 14.2042 2.18514 12.7458C0.728379 11.2875 0 9.52778 0 7.46667C0 5.40556 0.729167 3.64583 2.1875 2.1875C3.64583 0.729167 5.41042 0 7.48125 0C9.55208 0 11.3118 0.729167 12.7604 2.1875C14.209 3.64583 14.9333 5.40701 14.9333 7.47104C14.9333 8.30424 14.7972 9.10972 14.525 9.8875C14.2528 10.6653 13.8444 11.3944 13.3 12.075L21 19.7167L19.7167 21ZM7.49583 13.1833C9.07569 13.1833 10.4186 12.6243 11.5245 11.5063C12.6304 10.3882 13.1833 9.04167 13.1833 7.46667C13.1833 5.89167 12.6304 4.54514 11.5245 3.42708C10.4186 2.30903 9.07569 1.75 7.49583 1.75C5.89978 1.75 4.54312 2.30903 3.42586 3.42708C2.30862 4.54514 1.75 5.89167 1.75 7.46667C1.75 9.04167 2.30862 10.3882 3.42586 11.5063C4.54312 12.6243 5.89978 13.1833 7.49583 13.1833Z" fill="#A8AAAD"/>
                        </svg>
                    </span>
                    <input
                        type="text"
                        placeholder="Vyhledejte konkrétní tým nebo zápas"
                        value={searchTerm}
                        onChange={handleSearchChange}
                        className={`${styles.inputBackground} pl-6`}
                    />
                    <span className={`${styles.vyhledatPlaceholder}`}>
                        <button className={`${styles.vyhledatPlaceholder}`} type="submit" value="hledej">VYHLEDAT</button>
                    </span>
                </form>
            </div>
            {/*<div className="searchResults">*/}
            {/*    {results.map((result, index) => (*/}
            {/*        <div key={index}>*/}
            {/*            /!* Sem pak vlozit komponenty zapasu nebo tymu nebo neceho co vrati BE *!/*/}
            {/*            {result}*/}
            {/*        </div>*/}
            {/*    ))}*/}
            {/*</div>*/}
        </span>

        // <div className="card flex flex-wrap justify-content-center gap-3">
        //     <span className="p-input-icon-left">
        //         <i className="pi pi-search" />
        //         <InputText placeholder="Search" />
        //     </span>
        // </div>


    );
};

export default SearchBar;

