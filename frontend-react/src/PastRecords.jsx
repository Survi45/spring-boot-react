import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function PastRecords() {
  const { sno } = useParams();
  const [pastRecords, setPastRecords] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/candidates/{name}`)
      .then((response) => {
        setPastRecords(response.data);
      })
      .catch((error) => {
        console.error(
          `Error fetching past records for candidate with sno ${sno}:`,
          error
        );
      });
  }, [sno]);

  return (
    <div>
      <h1>Past Records for Candidate with Sno {sno}</h1>
      <ul>
        {pastRecords.map((record) => (
          <li key={record.recordId}></li>
        ))}
      </ul>
    </div>
  );
}

export default PastRecords;
