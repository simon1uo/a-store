<script lang="ts" setup>
import "element-plus/theme-chalk/display.css"
</script>

<template>
    <div class="content-header">
        <el-row justify="center">
            <el-col :lg="18" :xs="24">
                <div class="header-container">
                    <h1 class="store-header">这个商店，</h1>
                    <div class="store-subheader">
                        产品都称心，<br />体验更如意。
                    </div>
                </div>
            </el-col>
            <el-col :lg="6" class="hidden-md-and-down">
                <div class="sider-container">
                    <div class="store-chat">
                        <div class="store-chat-section">
                            <div class="store-chaticon-container">
                                <img
                                    src="./static/images/content-heaeder-support.png"
                                />
                            </div>
                            <div class="store-chat-container">
                                <div class="store-chat-title">
                                    需要协助选购？
                                </div>
                                <div class="store-chat-content">
                                    <a
                                        href="#"
                                        class="store-button active store-buttonlink"
                                    >
                                        咨询 Specialist 专家
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="store-neareststore">
                        <div class="as-svgicon-container">
                            <svg
                                viewBox="0 0 35 35"
                                class="as-svgicon as-svgicon-applestore as-svgicon-base as-svgicon-applestorebase"
                                role="img"
                                aria-hidden="true"
                                width="35px"
                                height="35px"
                            >
                                <path fill="none" d="M0 0h35v35H0z"></path>
                                <path
                                    d="M17.5 32.217a1.136 1.136 0 01-1-.576l-1.812-2.525c-.053-.087-.1-.116-.156-.116H10.5A4.5 4.5 0 016 24.5V10.518a4.5 4.5 0 014.5-4.5h14a4.5 4.5 0 014.5 4.5V24.5a4.5 4.5 0 01-4.5 4.5h-4.036a.149.149 0 00-.129.074l-1.867 2.609a1.108 1.108 0 01-.968.534zM10.5 7A3.519 3.519 0 007 10.518V24.5a3.5 3.5 0 003.5 3.5h4.036a1.151 1.151 0 011 .576l1.808 2.524c.128.213.211.17.284.042l1.868-2.61a1.125 1.125 0 01.968-.532H24.5a3.5 3.5 0 003.5-3.5V10.518A3.519 3.519 0 0024.5 7z"
                                ></path>
                                <path
                                    d="M21.35 14.635a2.326 2.326 0 00-1.078 1.94 2.215 2.215 0 001.353 2.051 4.733 4.733 0 01-.693 1.444c-.429.617-.9 1.246-1.584 1.246s-.858-.408-1.661-.408c-.77 0-1.045.419-1.672.419s-1.078-.573-1.584-1.29a6.312 6.312 0 01-1.056-3.363 2.719 2.719 0 012.541-3.021c.682 0 1.232.441 1.65.441s1.023-.463 1.782-.463a2.348 2.348 0 012.002 1.004zm-3.729-1.114c-.055 0-.1-.011-.143-.011 0-.033-.011-.11-.011-.187a2.26 2.26 0 01.561-1.378 2.19 2.19 0 011.485-.772 1.074 1.074 0 01.011.2A2.329 2.329 0 0119 12.794a1.967 1.967 0 01-1.379.727z"
                                ></path>
                            </svg>
                        </div>
                        <div class="store-neareststore-container">
                            <div class="store-neareststore-title">
                                前往 Apple Store 零售店
                            </div>
                            <div class="store-neareststore-content">
                                <a
                                    href="#"
                                    class="store-button store-buttonlink active more"
                                >
                                    查找附近的零售店
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<style lang="less" scoped>
.content-header {
    padding: 10px;
    .header-container {
        padding: 90px 0 74px 0;

        .store-header,
        .store-subheader {
            font-size: 48px;
            line-height: 1.08349;
            font-weight: 600;
            letter-spacing: -0.003em;
            font-family: SF Pro Display, SF Pro Icons, AOS Icons, Helvetica Neue,
                Helvetica, Arial, sans-serif;
            display: inline;
        }

        .store-subheader {
            color: #6e6e73;
        }
    }
    .sider-container {
        padding: 90px 0 74px 0;

        .store-chat {
            .store-chat-section {
                display: flex;
                flex-direction: row;
                padding-bottom: 16px;
                font-size: 12px;

                .store-chaticon-container {
                    img {
                        background: #fff;
                        border-radius: 50%;
                        width: 36px;
                        height: 36px;
                    }

                    padding-right: 8px;
                }

                .store-chat-container {
                    display: flex;
                    flex-direction: column;
                    justify-content: space-evenly;
                    .store-chat-title {
                        font-weight: 600;
                        padding-top: 0;
                    }
                }
            }
        }

        .store-neareststore {
            display: flex;
            flex-direction: row;
            padding-bottom: 16px;
            font-size: 12px;

            .as-svgicon-container {
                padding-right: 8px;
            }

            .store-neareststore-container {
                display: flex;
                flex-direction: column;
                justify-content: space-evenly;
                .store-neareststore-title {
                    font-weight: 600;
                }

                .store-neareststore-content {
                    .more::after {
                        content: ">";
                    }
                }
            }
        }
    }
}

.store-button {
    text-decoration: none;
    display: block;
}

.store-buttonlink.active {
    background: transparent;
    border: 0;
    color: #06c;
    cursor: pointer;
}

.store-buttonlink.active:hover {
    text-decoration: underline #06c;
}
</style>
