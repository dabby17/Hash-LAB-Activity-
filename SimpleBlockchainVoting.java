import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SimpleBlockchainVoting {

    private static class Vote {
        String voterId;
        String choice;
        String hash;

        Vote(String voterId, String choice) {
            this.voterId = voterId;
            this.choice = choice;
            this.hash = calculateHash();
        }

        private String calculateHash() {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest((voterId + choice).getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        List<Vote> blockchain = new ArrayList<>();

        blockchain.add(new Vote("Voter1", "Option A"));
        blockchain.add(new Vote("Voter2", "Option B"));
        blockchain.add(new Vote("Voter3", "Option A"));

        // Print the blockchain
        for (Vote vote : blockchain) {
            System.out.println("Voter ID: " + vote.voterId);
            System.out.println("Choice: " + vote.choice);
            System.out.println("Hash: " + vote.hash);
            System.out.println("--------------------");
        }
    }
}