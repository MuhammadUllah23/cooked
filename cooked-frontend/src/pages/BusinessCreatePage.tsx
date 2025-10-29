import { useParams, useNavigate } from "react-router-dom";

const BusinessCreatePage = () => {
  const { userId } = useParams();
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-background text-white flex flex-col items-center justify-center px-4">
      <div className="bg-navbar rounded-2xl p-8 w-full max-w-md shadow-lg">
        <div>Create a business for user {userId}</div>
        
      </div>
      
      <div className="mt-4 flex justify-center">
        <button
          onClick={() => navigate("/")}
          className="font-medium text-btn-primary hover:text-white transition-colors"
        >
          logout
        </button>
      </div>
    </div>
  );
};

export default BusinessCreatePage;