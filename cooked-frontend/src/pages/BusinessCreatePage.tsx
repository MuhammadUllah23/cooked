import { useParams } from "react-router-dom";

const BusinessCreatePage = () => {
  const { userId } = useParams();

  return <div>Create a business for user {userId}</div>;
};

export default BusinessCreatePage;