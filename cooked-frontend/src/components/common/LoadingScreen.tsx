import { motion } from "framer-motion";

interface LoadingScreenProps {
  fullScreen?: boolean; // if true, covers the entire viewport
}

const LoadingScreen: React.FC<LoadingScreenProps> = ({ fullScreen = false }) => {
  return (
    <div
      className={`
        flex flex-col items-center justify-center
        ${fullScreen ? "fixed top-0 left-0 w-full h-full bg-gray-900 z-50" : "w-full h-full"}
      `}
    >
      <motion.div
        className="w-12 h-12 border-4 border-blue-500 border-t-transparent rounded-full animate-spin"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ duration: 0.3 }}
      />
      <motion.p
        className="mt-4 text-white font-medium text-lg"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 0.2, duration: 0.5 }}
      >
        Loading, please wait...
      </motion.p>
    </div>
  );
};

export default LoadingScreen;
