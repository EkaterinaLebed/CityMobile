
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="fb-root"></div>
<header>
    <span id="logo">
        <a href="/"><img src="/citymobile/resources/logo.png" alt="CityMobile | " data-pin-nopin="true"></a>
    </span>

    <div class="globalTools">
        <ul class="buttons clearfix">
            <li class="hide"><a href="https://en.wikipedia.org/">Webmail</a></li>
            <li><a href="https://en.wikipedia.org/" target="_new">My Account</a></li>
            <li><a href="https://en.wikipedia.org/">Contact Us</a></li>
            <li><a href="https://en.wikipedia.org//">Feedback</a></li>
        </ul>

        <div class="middle clearfix">
            <ul class="icons clearfix">
                <li class="home-icon"><a href="https://en.wikipedia.org/">Home</a></li>
                <li class="contact-icon"><a href="https://en.wikipedia.org/">Contact Us</a></li>
                <li class="facebook-icon"><a href="https://en.wikipedia.org/">Find us on Facebook</a></li>
                <li class="insta-icon"><a href="https://en.wikipedia.org/">Follow us on Instagram</a></li>
            </ul>

            <div class="searchTool clear">
                <form id="globalSearch" method="post" action="https://en.wikipedia.org/">
                    <div class="hiddenFields">
                        <input type="hidden" name="ACT" value="8">
                        <input type="hidden" name="RES" value="">
                        <input type="hidden" name="meta" value="ZN7RLdjLIsw5zlIpmzKdRPnxl3hhKCg3gbJrrrWfOP9gJBDHN2WYQYxx31zzUlLJCRQEt1QWB6UVmQTu/Vy6I76PfWg/il/3uXoSjsHbQw5DmPNltkQDoi1kTxfD3WtWTQNbXqDzqjos0dkwVScM1fHRw9rziWNRKYQxEuEg5GienD/CN05iixL9OKF02QJxxAzELhY9uka8wEUuNBvsAPNOsn8tzAtiCdt0g/NoClRya11N6bcK3TQuYaknFe8JQrItTZF+6hDH4lXUZhhXinUl1Ff+tJgtq1LzgcTr+QzVA73Cm5BWLe5s1/g0RqXCpL92bdukHMf914UMPZ78RrJEixXXz3qe7lZ4hSilollpRrZj3Zs/OVfwM7wZoeeIKrNwp292W/GEefYuvfVw/Hdr5S0Znzt5XD46kWISy6k=">
                        <input type="hidden" name="site_id" value="1">
                        <input type="hidden" name="csrf_token" value="c7408af026676c251cd6c8cd121a6ab89177cf66">
                    </div>
                    <label>Find</label>
                    <input type="text" name="keywords" id="search_field" value="" class="idleField">
                    <button type="submit" id="btn_go" title="click to search site">Search</button>
                </form>
            </div>
        </div>
    </div>
</header>

