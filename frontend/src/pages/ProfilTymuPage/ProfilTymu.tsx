import React from 'react';
import NavBar from "../../components/NavBar/NavBar";
import Footer from "../../components/footer/Footer";
import HeaderTymu from "../../components/HeaderTymu/HeaderTymu";
import BodyTymu from "../../components/BodyTymu/BodyTymu";
import SearchBar from "../../components/SearchBar/SearchBarComponent";
import '../../components/BodyTymu/BodyTymu.css';
import {useLocation, useParams} from "react-router-dom";

const ProfilTymu = ({}) => {

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const id = searchParams.get('teamId');

    const { teamId } = useParams();

    return (
        <div className="ProfilTymuContainer BG">
            <NavBar PrihlaseniPage=""/>
            <SearchBar/>
                <div className="TymContainer">
                    <div>
                        <HeaderTymu teamId={id}/>
                        <BodyTymu teamId={id}/>
                    </div>
                </div>
            <Footer/>
        </div>
    );
};

export default ProfilTymu;