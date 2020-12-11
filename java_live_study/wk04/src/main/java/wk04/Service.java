package wk04;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.*;

public class Service {

    private GitHub github;
    private GHRepository githubRepo;
    private Map<String, Integer> percentage;

    public Service(String oAuthToken, String repo) {
        GitHubBuilder builder = new GitHubBuilder();
        try {
            github = builder.withOAuthToken(oAuthToken).build();
            githubRepo = github.getRepository(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            List<GHIssue> issueList = getAllIssues();
            for (GHIssue issue: issueList) {
                updateCommenters(issue);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCommenters(GHIssue issue) throws IOException {
        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment: comments) {
            if (hasAssignmentLink(comment)) {
                String id = comment.getUser().getLogin();
                updateCounts(id);
            }
        }
    }

    private void updateCounts(String id) {
        int num = 1;
        if (percentage.containsKey(id)) {
            num += percentage.get(id);
        }
        percentage.put(id, num);
    }

    private boolean hasAssignmentLink(GHIssueComment comment) {
        return comment.getBody().contains("https://");
    }

    private List<GHIssue> getAllIssues() throws IOException {
        return githubRepo.getIssues(GHIssueState.ALL);
    }
}