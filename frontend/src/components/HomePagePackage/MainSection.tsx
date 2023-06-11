import React from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './Navbar.css';
import './HomePage.css';

const MainSection = () => {
    return (
        <section className="MainSection">
            <div className="MainSectionContainer">
                <h1>Sledujte všechny<br/>
                    výsledky sportovních zápasů<br/>
                    na jednom místě</h1>
                <p className="perex">Aplikace, díky které můžete mít všechny sportovní<br/> výsledky na jednom
                    místě.<br/>
                    <br/>
                    Vyberte si které zápasy vás zajímají a dostanete<br/> všechny aktuální informace najednou.</p>
                <div className="imgFotbalista"></div>

                <Link to="/registrace">
                    <button className="button prihlaseni">Registruj se</button>
                </Link>

                <div className="sport-items">
                    <Link to="/fotbal">
                        <div className="sport-item fotbal-item">
                            <h3>Fotbal</h3>
                            <p>Výsledky fotbalových zápasů</p>
                        </div>
                    </Link>

                    <div className="sport-item space"></div>

                    <Link to="/basketbal">
                        <div className="sport-item">
                            <h3>Basketbal</h3>
                            <p>Výsledky basketbalových zápasů</p>
                        </div>
                    </Link>

                    <Link to="/hokej">
                        <div className="sport-item">
                            <h3>Hokej</h3>
                            <p>Výsledky hokejových zápasů</p>
                        </div>
                    </Link>

                    <Link to="/volejbal">
                        <div className="sport-item">
                            <h3>Volejbal</h3>
                            <p>Výsledky volejbalových zápasů</p>
                        </div>
                    </Link>
                </div>
            </div>
        </section>
    );
};

export default MainSection;
