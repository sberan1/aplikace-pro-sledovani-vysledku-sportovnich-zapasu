import "react-datepicker/dist/react-datepicker.css";
import React, { useState } from "react";
import DatePicker from "react-datepicker";
import axios from "axios";
import "./DatePicker.css";

const DatePickerComponent = () => {
    const DatePickerComponent = DatePicker as any;
    const [startDate, setStartDate] = useState(new Date());

    const handleChange = date => {
        setStartDate(date);
        sendDate(date);
    }

    const sendDate = date => {
        const formattedDate = date.toISOString().substring(0,10);

        axios.post('API_URL', { selectedDate: formattedDate })
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    return (
        <DatePicker selected={startDate} onChange={date => handleChange(date)} />
    );
};

export default DatePickerComponent;
