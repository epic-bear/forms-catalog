let occupationsByGender = {
    MALE: ["Mechanic", "Electrician", "Plumber", "Glazier", "Taxi driver"],
    FEMALE: ["Nurse", "Choreographer", "Receptionist", "Travel agent", "Social worker"]
};

function fillOccupations() {
    let gender = document.getElementById("gender").value;
    let occupationSelect = document.getElementById("occupations");
    occupationSelect.innerHTML = "";

    let occupations = occupationsByGender[gender];
    if (occupations) {
        for (let i = 0; i < occupations.length; i++) {
            let option = document.createElement("option");
            option.value = occupations[i];
            option.text = occupations[i];
            occupationSelect.appendChild(option);
        }
    }
}

document.getElementById("gender").addEventListener("change", fillOccupations);

fillOccupations();