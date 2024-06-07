import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./Reschdule.css";

const Reschedule = () => {
  const { sno } = useParams();
  const [newDateTime, setNewDateTime] = useState("");
  const navigate = useNavigate();

  const handleReschedule = async () => {
    try {
      await axios.put(`http://localhost:8081/reschedule/${sno}`, {
        newDateTime,
      });

      console.log(`Rescheduled candidate with sno ${sno} to ${newDateTime}`);

      navigate("/");
    } catch (error) {
      console.error("Error rescheduling candidate:", error);
    }
  };

  return (
    <div className="container">
      <h1>Reschedule Interview</h1>
      <p>Enter new date and time for candidate</p>
      <input
        className="date-time-input inputs"
        type="datetime-local"
        value={newDateTime}
        onChange={(e) => setNewDateTime(e.target.value)}
      />
      <button className="button-33" onClick={handleReschedule}>
        Reschedule
      </button>
    </div>
  );
};

export default Reschedule;
