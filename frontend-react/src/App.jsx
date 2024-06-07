import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./home";
import Reschedule from "./Reschedule";
import PastRecords from "./PastRecords";

const App = () => {
  return (
    <React.Fragment>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/reschedule/:sno" element={<Reschedule />}></Route>
        <Route path="/past-records/:sno" element={<PastRecords />} />
      </Routes>
    </React.Fragment>
  );
};

export default App;
