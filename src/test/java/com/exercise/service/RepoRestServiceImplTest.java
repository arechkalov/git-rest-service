package com.exercise.service;

import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import com.exercise.sevice.RepoRestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RepoRestServiceImplTest {

    @Autowired
    private RepoRestService repoRestService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetUser() {
        String body = "{\n" +
                "  \"login\": \"owner\",\n" +
                "  \"id\": 2824143" +
                "}";

        mockServer.expect(requestTo("https://api.github.com/users/owner")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

        RemoteUser owner = repoRestService.getUser("owner");

        mockServer.verify();
        assertThat(owner.getId(), equalTo("2824143"));
        assertThat(owner.getLogin(), equalTo("owner"));
    }

    @Test
    public void testGetRepos() {
        String body = "[\n" +
                "  {\n" +
                "    \"id\": 60836388,\n" +
                "    \"name\": \"ImageFilter\",\n" +
                "    \"full_name\": \"owner/ImageFilter\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"owner\",\n" +
                "      \"id\": 19854530,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/19854530?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/owner\",\n" +
                "      \"html_url\": \"https://github.com/owner\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/owner/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/owner/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/owner/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/owner/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/owner/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/owner/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/owner/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/owner/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/owner/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/owner/ImageFilter\",\n" +
                "    \"description\": null,\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/owner/ImageFilter\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/owner/ImageFilter/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/owner/ImageFilter/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/owner/ImageFilter/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/owner/ImageFilter/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/owner/ImageFilter/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/owner/ImageFilter/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/owner/ImageFilter/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/owner/ImageFilter/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/owner/ImageFilter/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/owner/ImageFilter/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/owner/ImageFilter/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/owner/ImageFilter/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/owner/ImageFilter/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/owner/ImageFilter/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/owner/ImageFilter/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/owner/ImageFilter/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/owner/ImageFilter/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/owner/ImageFilter/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/owner/ImageFilter/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/owner/ImageFilter/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/owner/ImageFilter/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/owner/ImageFilter/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/owner/ImageFilter/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/owner/ImageFilter/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/owner/ImageFilter/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/owner/ImageFilter/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/owner/ImageFilter/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/owner/ImageFilter/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/owner/ImageFilter/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/owner/ImageFilter/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/owner/ImageFilter/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/owner/ImageFilter/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/owner/ImageFilter/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/owner/ImageFilter/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/owner/ImageFilter/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/owner/ImageFilter/deployments\",\n" +
                "    \"created_at\": \"2016-06-10T09:15:07Z\",\n" +
                "    \"updated_at\": \"2016-06-10T09:15:07Z\",\n" +
                "    \"pushed_at\": \"2016-06-10T09:15:08Z\",\n" +
                "    \"git_url\": \"git://github.com/owner/ImageFilter.git\",\n" +
                "    \"ssh_url\": \"git@github.com:owner/ImageFilter.git\",\n" +
                "    \"clone_url\": \"https://github.com/owner/ImageFilter.git\",\n" +
                "    \"svn_url\": \"https://github.com/owner/ImageFilter\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 0,\n" +
                "    \"stargazers_count\": 0,\n" +
                "    \"watchers_count\": 0,\n" +
                "    \"language\": null,\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 0,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 0,\n" +
                "    \"forks\": 0,\n" +
                "    \"open_issues\": 0,\n" +
                "    \"watchers\": 0,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  }\n" +
                "]";

        mockServer.expect(requestTo("https://api.github.com/users/owner/repos")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

        List<RemoteRepository> remoteRepositories = repoRestService.getRepos("owner");

        mockServer.verify();

        assertThat(remoteRepositories.isEmpty(), is(false));
        assertThat(remoteRepositories.size(), is(1));
        assertThat(remoteRepositories.get(0).getDescription(), equalTo(null));
        assertThat(remoteRepositories.get(0).getCreatedAt(), equalTo("2016-06-10T09:15:07Z"));
        assertThat(remoteRepositories.get(0).getCloneUrl(), equalTo("https://github.com/owner/ImageFilter.git"));
        assertThat(remoteRepositories.get(0).getFullName(), equalTo("owner/ImageFilter"));
    }

    @Test(expected = HttpMessageNotReadableException.class)
    public void testGetUserParseFailed() {
        String body = "unparsable string";

        mockServer.expect(requestTo("https://api.github.com/users/owner")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

        repoRestService.getUser("owner");

        mockServer.verify();
    }
}