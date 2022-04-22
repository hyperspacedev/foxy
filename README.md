# Foxy

An idiomatic Kotlin wrapper for the Mastodon API

> :warning: This project is not in a production-ready state.

## Getting started

Add the following information to your `build.gradle.kts` file:

```kotlin
// MERGE THIS WITH YOUR REPOSITORIES ENTRY
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/hyperspacedev/foxy")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

// MERGE THIS WITH YOUR DEPENDENCIES ENTRY
dependencies {
    implementation("dev.hyperspace:foxy:<version>")
    implementation(kotlin("script-runtime"))
}
```

Create a personal access token with access to `packages`. In your `gradle.properties` file, add the following:

```
gpr.user=yourGitHubUserName
gpr.key=yourGitHubToken
```

Replace `<version>` with the version of Foxy you'd like to download. Run `./gradlew build` to sync your changes and
download the dependencies.

### Adding the library

> TBD

### Starting an authentication flow

Before you can make authenticated requests, you must first obtain an access token from the end user or client
credentials. The process is relatively straightforward.

Start by creating a Foxy application and calling the `startOAuthFlow` method on the Foxy object, specifying the instance
you'd like to authenticate and authorize with:

```kotlin
val myApplication = FoxyApp("My Great Mastodon App", "https://mastodon.example")


// The authentication URL is returned here.
val authUrl = Foxy.startOAuthFlow(
    "mastodon.example",
    myApplication,
    redirectUri = "urn:ietf:wg:oauth:2.0:oob"
)

```

Alternatively, you can use the `FoxyAuthBuilder` approach:

```kotlin
// The authentication URL is returned here.
val authUrl = Foxy.startOAuthFlow {
    instance = "mastodon.social"
    redirectUri = "https://example.com/oauth"

    appName("My Great Mastodon App")
    appWebsite("https://mastodon.example")

    scopes {
        add("read")
        add("write")
        add("follow")
    }
}

```

The authentication URL will be returned in either approach, which can be opened in a web browser to allow an end user to
sign in and authorize the app you created access to your account. To finish the workflow, call `finishOAuthFlow`,
specifying how to grant permission, along with the redirect URL that contains the code needed to create the access
token:

```kotlin
// We don't want user-level access in this example, so ClientCredentials will do.
Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredentials)
```

The access token will be privately stored away with an integrity stamp which expires after a period of time. This helps
ensure that the access token isn't compromised when being used in Foxy.

This authorization scheme has been set up so that it is up to the developer how to open the authorization URL and how to
redirect the user back to your app afterwards.

### Making a request

Making a request is as simple as calling `request<T>`:

```kotlin
val response = Foxy.request<Timeline> {
    // Specify a GET request
    method = HttpMethod.Get

    // Set the endpoint to the home timeline endpoint
    getTimeline(FoxyTimelineScope.Home())

    // Specify request parameters with either parameter(key, value) or parameters(builder)
    parameter("limit", 10)
}
```

The return type is a `MastodonResponse`, a sealed class which handles errors for you. This can be used to inform the
user of any potential errors from the request.

```kotlin
when (response) {

    // The success event includes the entity with the specified type.
    is MastodonResponse.Success -> println(response.entity.first().content)

    // The error event includes the Mastodon error type, to print out errors.
    is MastodonResponse.Error -> println(response.error.error)
}

// Or, hoist the type, if you don't care about errors. This will return a nullable type.
val timeline: Timeline? = response.hoistEntityOrNull()

```

Note that there are various methods for setting the endpoint, such as `timeline`, which fetches a timeline, and `info`,
which fetches common information such as the current user and the instance they live on.If you need to refer to a custom
endpoint, you can declare it, provided that you opt in to the `Dangerous` annotation:

```kotlin
// The Dangerous annotation requires opt-in to acknowledge that you are aware of the unintended side effects of using
// the method in question.
@OptIn(Dangerous::class)
suspend fun getFreddy(): Animatronic? =
    Foxy.request<Animatronic> {
        method = HttpMethod.Get
        customEndpoint("/api/v2/animatronics/1") // This is the @Dangerous annotated method.
    }.hoistEntityOrNull()

```

## Licensing

Foxy is licensed under the Non-Violent Public License, the same license used in Hyperspace Desktop, Chica, and
Starlight. This is a semi-permissive license that allows modifications and redistributions as long as the software is
not used to harm another person or cause conflict. You can read your rights in the attached LICENSE file.