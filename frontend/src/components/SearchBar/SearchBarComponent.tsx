import React, {FormEvent, useState, useEffect} from 'react';
import '../HomePagePackage/Navbar.css';
import '../../App.css';

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
        <nav className="NavBar">
            <form onSubmit={handleSubmit} className="SearchForm">
                <input
                    type="text"
                    placeholder="Vyhledejte konkrétní tým nebo zápas"
                    value={searchTerm}
                    onChange={handleSearchChange}
                />
                <button type="submit" value="hledej">VYHLEDAT</button>
            </form>

            <div className="SearchResults">
                {results.map((result, index) => (
                    <div key={index}>
                        {/* Sem pak vlozit komponenty zapasu nebo tymu nebo neceho co vrati BE */}
                        {result}
                    </div>
                ))}
            </div>
        </nav>
    );
};

export default SearchBar;

