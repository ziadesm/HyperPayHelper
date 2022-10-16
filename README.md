# Contributing to HyperPayDemo

[![](https://jitpack.io/v/ziadesm/HyperPayDemo.svg)](https://jitpack.io/#ziadesm/HyperPayDemo)


Thanks for your interest in our Library. Our goal is to bring fast, reliable, and trouble-free open-source android projects to all communities.
We love your input! We want to make every contribution to this project as transparent and effortless as possible.
Please note that we have a code of conduct that we need to follow moving forward in all your interactions with the project.

- Reporting a bug
- Discussing the current state of the code
- Submitting a fix
- Proposing new features
- Becoming a maintainer

## We Develop with Github
We use github to host code, to track issues and feature requests, as well as accept pull requests.

## How to use our code
# Download
--------
Gradle:

```gradle
repositories {
   google()
   mavenCentral()
   maven { url 'https://jitpack.io' }
}

dependencies {
   implementation 'com.github.ziadesm:HyperPayDemo:1.2.5'
}
```

# Simple use of our HyperPayDemo
```kotlin
PaymentHelper.Builder(this, this)
    .paymentType(hashSetOf("VISA", "MASTER", etc ...))
    .checkoutId(your checkout ID that getting from your server)
    .shopperResultUrl(applicationId)
    .testMode(true)
    .build()
```

## We Use [Github Flow](https://guides.github.com/introduction/flow/index.html), So All Code Changes Happen Through Pull Requests

Pull requests are the best way to propose changes to the codebase (we use [Github Flow](https://guides.github.com/introduction/flow/index.html)). We actively welcome your pull requests:

1. Fork the repository and create your branch from `main`.
2. If you've added code that should be tested, add tests.
3. If you've changed APIs, update the documentation.
4. Ensure the test suite passes.
5. Make sure your code lints.
6. Issue that pull request!
7. Always add a `README` and/or `requirements.txt` to your added code.

## Report bugs using Github's [issues](https://github.com/ziadesm/HyperPayDemo/issues)
We use GitHub issues to track public bugs. Report a bug by [opening a new issue](); it's that easy!

*Great Bug Reports* tend to have:

- A quick summary and/or background
- Steps to reproduce
    - Be specific!
    - Give sample code if you can.
- What you expected would happen
- What actually happens
- Notes (possibly including why you think this might be happening, or stuff you tried that didn't work)

People love thorough bug reports. I'm not even kidding.

## References
This document was adapted from the our contribution guidelines for [Facebook](https://www.facebook.com/AlalmiyaAlhura)
