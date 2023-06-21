import NavBar from "../../components/NavBar/NavBar";
import DatePickerComponent from "../../components/DatePicker/DatePicker";
import SearchBar from "../../components/SearchBar/SearchBarComponent";
import React, {useState} from "react";
import DatePicker from "../../components/DatePicker/DatePicker";

const Dashboard = ({}) => {

    const [date, setDate] = useState([]);
    const { formattedDateToReturn, render } = DatePicker();

    return (
        <div className="PrihlaseniPaneContainer BG">
            <NavBar PrihlaseniPage=""/>
            {render}
            <SearchBar/>
        </div>
    );
}