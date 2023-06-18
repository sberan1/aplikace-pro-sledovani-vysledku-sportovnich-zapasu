import "react-datepicker/dist/react-datepicker.css";
import React, { useState } from "react";
import DatePicker from "react-datepicker";
import axios from "axios";
import "./DatePicker.css";
import { parse, format } from 'date-fns';

const DatePickerComponent = () => {
    const DatePickerComponent = DatePicker as any;
    const [startDate, setStartDate] = useState(new Date());
    const [items, setItems] = useState([]); // pole se všemi položkami
    const [filteredItems, setFilteredItems] = useState([]); // pole s filtrovanými položkami
    const [formattedDateToReturn, setFormattedDateToReturn] = useState("");

    const handleChange = date => {
        setStartDate(date);
        sendDate(date);
        filterItems(date);
        formatDateToString(date);
    }

    const filterItems = date => {
        const filtered = items.filter(item => new Date(item.date).setHours(0,0,0,0) === date.setHours(0,0,0,0));
        setFilteredItems(filtered);
    }// Pak byste místo 'items' zobrazovali 'filteredItems' v render metode.

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

    const formatDateToString = date => {
        let dateString = date.toLocaleDateString('cs-CZ'); // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/
        dateString = '21. 6. 2023';
        const formatDate = parse(dateString, 'd. M. yyyy', new Date());
        const formattedDate = format(formatDate, 'yyyy-MM-dd');
        setFormattedDateToReturn(formattedDate);
    }

    return {
        formattedDateToReturn,
        render:(
                <DatePicker selected={startDate} onChange={date => handleChange(date)} />
        )
    }


};

export default DatePickerComponent;
