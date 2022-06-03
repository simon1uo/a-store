<template>
    <footer class="globalfooter">
        <div class="globalfooter-content">
            <nav class="globalfooter-breadcrumbs">
                <a href="#" class="globalfooter-breadcrumbs-home">
                    <span class="globalfooter-breadcrumbs-home-icon"></span>
                    <span class="globalfooter-breadcrumbs-home-label"
                        >Apple</span
                    >
                </a>
                <div class="globalfooter-breadcrumbs-path">
                    <ol
                        class="globalfooter-breadcrumbs-list"
                        typeof="BreadcrumbList"
                    >
                        <li
                            class="globalfooter-breadcrumbs-item"
                            property="itemListElement"
                            typeof="ListItem"
                        >
                            <span property="item">Apple Store 在线商店</span>
                            <meta property="position" content="1" />
                        </li>
                    </ol>
                </div>
            </nav>
        </div>

        <section class="globalfooter-mini">
            <div class="globalfooter-mini-shop">
                <p>
                    更多选购方式：<span class="nowrap"
                        ><a href="#"> 查找你附近的 Apple Store 零售店 </a></span
                    >及<a href="#">更多门店</a>，或者致电
                    <span>400-666-8800</span>。
                </p>
            </div>
            <div class="globalfooter-mini-locale"></div>
            <div class="globalfooter-mini-legal">
                <div class="globalfooter-mini-legal-copyright">
                    Copyright © 2022 Apple Inc. 保留所有权利。
                </div>
                <div class="globalfooter-mini-legal-links">
                    <a href="#" class="globalfooter-mini-legal-link">隐私政策</a
                    ><a href="#" class="globalfooter-mini-legal-link"
                        >使用条款</a
                    ><a href="#" class="globalfooter-mini-legal-link"
                        >销售政策</a
                    ><a href="#" class="globalfooter-mini-legal-link"
                        >法律信息</a
                    ><a href="#" class="globalfooter-mini-legal-link"
                        >网站地图</a
                    >
                </div>
            </div>
        </section>
    </footer>
</template>

<script lang="ts" setup></script>

<style lang="less" scoped>
.globalfooter {
    --footer-font-size: 6px;
    --footer-background: #f5f5f7;
    --footer-border-color: #d2d2d7;
    --footer-text-color: #6e6e73;
    --footer-link-color: #424245;
    --footer-directory-title-color: #1d1d1f;
    --footer-directory-title-color-hover: #000;
    --footer-mini-shop-link-color: #0066cc;
    .globalfooter-content {
        padding-top: 20px;
        .globalfooter-breadcrumbs {
            padding: 17px 0;
            position: relative;
            color: var(--footer-link-color);

            .globalfooter-breadcrumbs-home {
                position: absolute;
                left: 0;
                top: 17px;
                z-index: 2;

                .globalfooter-breadcrumbs-home-icon {
                    width: 14px;
                    height: 18px;
                    display: block;
                    background-size: 14px 72px;
                    background: url("../static/images/footer-breadcrumbs-logo.svg")
                        no-repeat;
                    position: relative;
                    z-index: 2;
                    color: transparent;
                    background-position-y: -36px;
                }

                .globalfooter-breadcrumbs-home-label {
                    position: absolute;
                    overflow: hidden;
                    height: 1px;
                    width: 1px;
                    padding: 0;
                    border: 0;
                }
            }

            .globalfooter-breadcrumbs-path {
                .globalfooter-breadcrumbs-list {
                    padding-left: 25px;
                    .globalfooter-breadcrumbs-item::before {
                        width: 8px;
                        height: 18px;
                        background-size: 8px 36px;
                        background-repeat: no-repeat;
                        background-image: url(../static/images/footer-breadcrumbs-right-icon.svg);
                        margin-right: 11px;
                        content: "";
                        display: inline-block;
                        vertical-align: middle;
                        margin-top: -1px;
                        background-position-y: 100%;
                    }

                    .globalfooter-breadcrumbs-item {
                        font-size: var(--footer-font-size);
                        font-weight: 400;
                        color: var(--footer-text-color);
                    }
                }
            }
        }
    }

    .globalfooter-mini {
        padding: 34px 0 21px 0;
        .globalfooter-mini-shop {
            margin-bottom: 7px;
            padding-bottom: 8px;
            border-bottom: 1px solid var(--footer-border-color);
            p {
                font-size: var(--footer-font-size);
                font-weight: 300;
                a {
                    color: var(--footer-mini-shop-link-color);
                }
                a:hover {
                    text-decoration: underline
                        var(--footer-mini-shop-link-color);
                }
            }
        }

        .globalfooter-mini-legal {
            font-size: var(--footer-font-size);
            font-weight: 300;
            margin-top: 5px;
            display: flex;
            flex-direction: row;
            align-items: center;
            .globalfooter-mini-legal-copyright {
                color: var(--footer-text-color);
                float: left;
                margin-right: 30px;
            }

            .globalfooter-mini-legal-links {
                .globalfooter-mini-legal-link {
                    font-weight: 400;
                    color: var(--footer-link-color);
                    border-right: 1px solid var(--footer-border-color);
                    margin-right: 7px;
                    padding-right: 10px;
                    display: inline-block;
                    white-space: nowrap;
                }
                .globalfooter-mini-legal-link:hover {
                    text-decoration: underline var(--footer-border-color);
                }
                .globalfooter-mini-legal-link:last-child {
                    border: none;
                }
            }
        }
    }
}
</style>
