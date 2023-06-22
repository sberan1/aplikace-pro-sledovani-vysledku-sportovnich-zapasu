import React, { useState, useEffect } from 'react';
import './BodyTymu.css';
import {MatchSourceType} from "../Enums";
import MatchList from "../MatchList";
import axios from "axios";
import {SelectButton} from "primereact/selectbutton";

const BodyTymu = ({teamId}) => {
    const options = ['PROGRAM', 'VÝSLEDKY'];
    const [value, setValue] = useState(options[0]);

    if (value === options[0]) {
        return (
            <div className="BodyTymuContainer">

                <div className="header">
                    <SelectButton value={value}
                                  onChange={(e) => setValue(e.value) }
                                  options={options}
                                  style={{height: '5px', width: '200px'}}
                    />
                    <div className="select-container">
                        <div className="select-button" id="myButton">
                            <span className="option selected">Option 1</span>
                            <span className="option">Option 2</span>
                        </div>
                    </div>
                </div>
                <MatchList type={MatchSourceType.TeamPast} webParams={teamId}/>
            </div>
        );
    }
    else {
        return (
            <div className="BodyTymuContainer">

                <div className="header">
                    <SelectButton value={value} onChange={(e) => setValue(e.value)} options={options}/>
                </div>
                <MatchList type={MatchSourceType.TeamFuture} webParams={teamId}/>
            </div>
        );
    }
}
export default BodyTymu;