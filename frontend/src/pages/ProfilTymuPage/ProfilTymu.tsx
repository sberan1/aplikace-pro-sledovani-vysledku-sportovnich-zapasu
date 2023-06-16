import React from 'react';
import NavBar from "../../components/NavBar/NavBar";
import Footer from "../../components/footer/Footer";
import HeaderTymu from "../../components/HeaderTymu/HeaderTymu";
import BodyTymu from "../../components/BodyTymu/BodyTymu";
import SearchBar from "../../components/SearchBar/SearchBarComponent";
import '../../components/BodyTymu/BodyTymu.css';


const ProfilTymu = ({teamId}) => {
    return (
        <div className="ProfilTymuContainer BG">
            <NavBar PrihlaseniPage=""/>
            <SearchBar/>
                <div className="TymContainer">
                    <div>
                        <HeaderTymu teamId={undefined} userId={undefined}/>
                        <BodyTymu teamId={undefined}/>
                    </div>
                </div>
            <Footer/>
        </div>
    );
};

export default ProfilTymu;