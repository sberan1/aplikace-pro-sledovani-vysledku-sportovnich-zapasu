import React, {FormEvent, useState, useEffect} from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import './Navbar.css';
import '../../App.css';

interface NavbarProps {
    HomePage: unknown,
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const Navbar = ({ HomePage, PrihlaseniPage, RegistracePage }: NavbarProps) => {
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
        <nav>
            <div className="logo">
                <span className="navbar-logo"></span>
            </div>

            <form onSubmit={handleSubmit} className="SearchForm">
                <input
                    type="text"
                    placeholder="Vyhledejte konkrétní tým nebo zápas"
                    value={searchTerm}
                    onChange={handleSearchChange}
                />
                <button type="submit" value="hledej">Hledej</button>
            </form>

            <div className="buttons">
                <Link to="/registrace">
                    <button className="button registrace">Registrace</button>
                </Link>
                <Link to="/prihlaseni">
                    <button className="button prihlaseni">Přihlásit se</button>
                </Link>
            </div>

        </nav>
    );
};

export default Navbar;

