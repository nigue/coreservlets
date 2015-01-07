package coreservlets;

import coreservlets.utils.SearchUtilities;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Takes a search string and a search engine name, sending the query to that
 * search engine. Illustrates manipulating the response status code. It sends a
 * 302 response (via sendRedirect) if it gets a known search engine and sends an
 * error page if not.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Android, JSF, PrimeFaces, Ajax,
 * GWT, and Java</a>.
 */
@ManagedBean
public class SearchController {

    private String searchString = "", searchEngine;

    public String getSearchString() {
        return (searchString);
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString.trim();
    }

    public String getSearchEngine() {
        return (searchEngine);
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public List<String> getSearchEngineNames() {
        return (SearchUtilities.searchEngineNames());
    }

    public String doSearch() throws IOException {
        String folder = "pages/searchController/";
        if (searchString.isEmpty()) {
            return (folder + "no-search-string");
        }
    // The URLEncoder changes spaces to "+" signs and other
        // non-alphanumeric characters to "%XY", where XY is the
        // hex value of the ASCII (or ISO Latin-1) character.
        // Browsers always URL-encode form values, and the
        // getParameter method decodes automatically. But since
        // we're just passing this on to another server, we need to
        // re-encode it to avoid characters that are illegal in
        // URLs. 
        searchString = URLEncoder.encode(searchString, "utf-8");
        String searchURL
                = SearchUtilities.makeURL(searchEngine, searchString);
        if (searchURL != null) {
            ExternalContext context
                    = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response
                    = (HttpServletResponse) context.getResponse();
            response.sendRedirect(searchURL);
            return (null);
        } else {
            return (folder + "unknown-search-engine");
        }
    }
}
