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
import axios from "axios";
import {ProgressSpinner} from "primereact/progressspinner";


const Dashboard = () => {

    const [ModalIsOpen, setModalIsOpen] = useState(false);
    const [suggestedTeams, setSuggestedTeams] = useState(null);
    const [favoriteTeams, setFavoriteTeams] = useState([]);

    const fetchFavTeams = async () => {
        const response = await axios.get(`http://localhost:8080/user/getFavouriteTeams`);
        setFavoriteTeams(response.data);
    }
    const fetchSuggestedTeams = async () => {
        const response = await axios.get(`http://localhost:8080/auth/OpenAiCall`);
        setSuggestedTeams(response.data);
    }
    useEffect(() => {
       fetchFavTeams();
    }, []);

    const openModal = () => {
        setModalIsOpen(true);
        fetchSuggestedTeams();
    }

    const closeModal = () => {
        setModalIsOpen(false);
        setSuggestedTeams(null);
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
                        </div>
                        <div className="MatchListContainer">
                            <MatchList type={MatchSourceType.User} webParams={""}/>
                        </div>
                    </div>

                    <div className="TymyContainer">
                        <h2>Oblíbené týmy</h2>
                        <div className="tymy">
                            {favoriteTeams.map(team => (
                                <OblibenyTym
                                    key={team.id}
                                    teamId={team.id}
                                    teamName={team.name}
                                    isFavourite={true}
                                    teamLogo={team.logo
                                }/>
                            ))}
                        </div>
                        <button onClick={openModal} className="BtnDoporuceni">Chci doporučit týmy na míru</button>
                        <Modal
                            isOpen={ModalIsOpen}
                            onRequestClose={closeModal}
                            contentLabel="Změna hesla"
                            className="Modal"
                            overlayClassName="Overlay"
                        >
                            <div className="ModalContainer modalOblibene">
                                <h2>Podle Vašich oblíbených týmů, pro Vás máme doporučení na tyto týmy:</h2>

                            {suggestedTeams !== null ?(
                                <div className="tymy">
                                        {suggestedTeams.map((team, id)=> (
                                            <OblibenyTym
                                                key={team.id}
                                                teamId={team.id}
                                                teamName={team.name}
                                                isFavourite={false}
                                                teamLogo={team.logo}
                                            />
                                        ))}
                                </div>
                            ) : (
                                        <ProgressSpinner/>
                            )}
                            </div>
                            <button onClick={closeModal} className="">ZAVŘÍT</button>
                        </Modal>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default Dashboard;