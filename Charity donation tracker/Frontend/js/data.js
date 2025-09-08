// Hardcoded demo data
const users = [
  {username:"donor1", password:"donor123", role:"donor"},
  {username:"ngo1", password:"ngo123", role:"ngo"},
  {username:"admin1", password:"admin123", role:"admin"}
];

const donors = [
  {username:"donor1", donations:[]}
];

const ngos = [
  {username:"ngo1", causes:[
      {title:"Books for Schools", goal:5000, collected:2000, active:true},
      {title:"Clean Water", goal:10000, collected:3000, active:true}
  ]}
];
