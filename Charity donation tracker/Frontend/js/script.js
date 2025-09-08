// LOGIN
document.getElementById("loginForm").addEventListener("submit", async function(e){
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();
  const role = document.getElementById("role").value;
  const errorMsg = document.getElementById("error");

  try {
    const res = await fetch("http://localhost:9090/api/login", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify({username, password, role})
    });

    const data = await res.json();
    if(data.status==="success"){
      if(role==="donor") window.location.href = "./donor.html";
      else if(role==="ngo") window.location.href = "./ngo.html";
      else if(role==="admin") window.location.href = "./admin.html";
    } else {
      errorMsg.textContent="❌ Invalid username, password, or role!";
    }
  } catch(err){
    errorMsg.textContent="❌ Server not running!";
  }
});

// DONOR PAGE: fetch causes from backend
if(document.getElementById("donorPage")){
  fetch("http://localhost:9090/api/causes")
    .then(res => res.json())
    .then(ngos => {
      const container = document.getElementById("donorPage");
      ngos.forEach((cause,index)=>{
        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML=`
          <h3>${cause.title}</h3>
          <p>Goal: ${cause.goal} | Collected: ${cause.collected}</p>
          <div class="progress-bar"><div class="progress" style="width:${(cause.collected/cause.goal)*100}%"></div></div>
          <input type="number" placeholder="Donate Amount" id="amt${index}">
          <button class="btn" onclick="donate(${index})">Donate</button>
        `;
        container.appendChild(card);
      });
    })
    .catch(err => console.log("Error fetching causes:", err));
}

// Donate function
function donate(index){
  const input = document.getElementById("amt"+index);
  const amt = parseInt(input.value);
  if(amt>0){
    fetch("http://localhost:9090/api/donate", {
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body: JSON.stringify({causeIndex:index, amount:amt})
    })
    .then(res=>res.text())
    .then(msg=>{
      alert(msg);
      window.location.reload();
    })
    .catch(err => alert("❌ Donation failed! Server not running."));
  } else alert("❌ Enter valid amount");
}
