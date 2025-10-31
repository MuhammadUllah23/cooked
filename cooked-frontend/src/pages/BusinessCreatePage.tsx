import { useParams, useNavigate } from "react-router-dom";
import LogoutButton from "../components/LogoutButton";

const BusinessCreatePage = () => {
  const { userId } = useParams();

  return (
    <div className="min-h-screen bg-background text-white flex flex-col items-center justify-center px-4">
      <div className="bg-navbar rounded-2xl p-8 w-full max-w-md shadow-lg">
        <div>Create a business for user {userId}</div>
        
      </div>
      
      <div className="mt-4 flex justify-center">
        <LogoutButton />
      </div>
    </div>
  );
};

export default BusinessCreatePage;