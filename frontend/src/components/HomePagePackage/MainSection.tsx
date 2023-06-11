import React from 'react';
// @ts-ignore
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import './Navbar.css';

const MainSection = () => {
    return (
        <section>
            <h1>Sledujte všechny<br />
                výsledky sportovních zápasů<br />
                na jednom místě</h1>
            <p>Aplikace, díky které můžete mít všechny sportovní výsledky na jednom místě.<br />
                <br />
                Vyberte si které zápasy vás zajímají a dostanete všechny aktuální informace najednou.</p>
            <img src="../../assets/FotbalistaHomePage.png" alt="FotbalistaHomePage"/>

            <div className="sport-items">
                <div className="sport-item">
                    <Link to="/fotbal">
                        <h3>Fotbal</h3>
                        <p>Výsledky fotbalových zápasů</p>
                    </Link>
                </div>
                <div className="sport-item">
                    <Link to="/basketbal">
                        <h3>Basketbal</h3>
                        <p>Výsledky basketbalových zápasů</p>
                    </Link>
                </div>
                <div className="sport-item">
                    <Link to="/hokej">
                        <h3>Hokej</h3>
                        <p>Výsledky hokejových zápasů</p>
                    </Link>
                </div>
                <div className="sport-item">
                    <Link to="/volejbal">
                        <h3>Volejbal</h3>
                        <p>Výsledky volejbalových zápasů</p>
                    </Link>
                </div>

            </div>
        </section>
    );
};

export default MainSection;
