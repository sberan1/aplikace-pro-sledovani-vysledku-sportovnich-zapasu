import NavBar from "../../components/NavBar/NavBar";
import SearchBar from "../../components/SearchBar/SearchBarComponent";
import React, {useEffect, useState} from "react";
import DatePicker from "../../components/DatePicker/DatePicker";
import './Dashboard.css';
import MatchList from "../../components/MatchList";
import {MatchSourceType} from "../../components/Enums";
import {id} from "date-fns/locale";
import OblibenyTym from "../../components/OblibenyTym/OblibenyTym";
import Footer from "../../components/footer/Footer";
import Modal from "react-modal";
import '../../components/NavBar/Modal.css'
import FavoriteTeamBtn from "../../components/Buttons/FavoriteTeamBtn/FavoriteTeamBtn";


const Dashboard = ({userId}: {
                       userId: number
                   }
) => {

    const [date, setDate] = useState([]);
    const {formattedDateToReturn, render} = DatePicker();

    const [ModalIsOpen, setModalIsOpen] = useState(false);

    const [favoriteTeams, setFavoriteTeams] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:3000/favourites/${userId}`)
            .then(response => response.json())
            .then(favoriteTeams => setFavoriteTeams(favoriteTeams));
    }, [userId]);

    const openModal = () => {
        setModalIsOpen(true);
    }

    const closeModal = () => {
        setModalIsOpen(false);
    }


    return (
        <div className="DashboardContainer BG">
            <NavBar PrihlaseniPage=""/>
            <SearchBar/>

            <div className="DashboardBodyContainer">
                <h1>Váš dashboard</h1>
                <p className="perex">Zde uvidíte všechny Vámi sledované zápasy a oblíbené týmy</p>

                <div className="DashboardBody">
                    <div className="ZapasyContainer">
                        <h2>Sledované zápasy</h2>
                        <div className="header">
                            <div>Program/výsledky</div>
                            <div>{render}</div>
                        </div>
                        <div className="MatchListContainer">
                            {/* jak to udělat aby se zobrazili jen zápasy týmů, které uživatel sleduje? */}
                            <MatchList type={MatchSourceType.League} webParams={`&date=${date}&league=${id}`}/>
                        </div>
                    </div>

                    <div className="TymyContainer">
                        <h2>Oblíbené týmy</h2>
                        <div className="tymy">
                            {favoriteTeams.map(team => (
                                <OblibenyTym
                                    key={team.teamId}
                                    teamId={team.teamId}
                                    teamName={team.teamName}
                                    userId={userId}
                                    isFavorite={true}
                                />
                            ))}
                        </div>
                        <button onClick={openModal}>Chci doporučit týmy na míru</button>
                        <Modal
                            isOpen={ModalIsOpen}
                            onRequestClose={closeModal}
                            contentLabel="Změna hesla"
                            className="Modal"
                            overlayClassName="Overlay"
                        >
                            <div className="ModalContainer modalOblibene">
                                <h2>Podle Vašich oblíbených týmů, pro Vás máme doporučení na tyto týmy:</h2>
                                <div className="tymy">
                                    {/* jak sem napojit AI??? */}
                                    {favoriteTeams.map(team => (
                                        <OblibenyTym
                                            key={team.teamId}
                                            teamId={team.teamId}
                                            teamName={team.teamName}
                                            userId={userId}
                                            isFavorite={false}
                                        />
                                    ))}
                                </div>
                                <button onClick={closeModal} className="">ZAVŘÍT</button>
                            </div>
                        </Modal>
                    </div>
                </div>

            </div>
            <Footer/>
        </div>
    );
}

export default Dashboard;