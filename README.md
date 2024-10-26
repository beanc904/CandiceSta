<p align="center">
    <a href="https://github.com/beanc904/CandiceSta"><img width="200" src="https://github.com/beanc904/CandiceSta/blob/master/src/main/resources/com/beanc/images/icon.png" alt="CandiceSta logo"></a>
</p>

<p align="center">
    <a href="https://github.com/beanc904/CandiceSta/releases"><img src="https://img.shields.io/github/release/beanc904/CandiceSta" alt="Release version"></a>
    <a href="https://github.com/beanc904/CandiceSta/blob/master/LICENSE"><img src="https://img.shields.io/github/license/beanc904/CandiceSta" alt="License"></a>
    <a href="https://github.com/beanc904/CandiceSta/tree/master/.Doc/documents"><img src="https://img.shields.io/badge/documents-releases-yellow" alt="Documents"></a>
</p>

# Candice Hygiene Statistic

## 目录

- [项目简介](#项目简介)
- [使用指南](#使用指南)
- [实例](#实例)
- [贡献指南](#贡献指南)
- [许可证信息](#许可证信息)
- [维护者信息](#维护者信息)
- [鸣谢](#鸣谢)
- [构建状态](#构建状态)
- [版本信息](#版本信息)

## 项目简介

本项目主要为解决江苏某学校约定俗成的非官方清卫统计规则的耗时耗力而诞生，且仅作为成果，谨献给最可爱的小呆熊。软件中包括，但不限于，每日清卫的录入与查询操作。目前软件处于alpha测试阶段，如若被小呆熊接纳并持续使用，项目将会进一步维护并开发新功能。

关于软件的最初[设计蓝图](#软件开发指向)。

## 使用指南

### 下载与使用

移步项目的`release`页面下载最新版本的发布软件，完成安装后，初始化即可使用。

### 问题

- 安装完成后依旧无法启动？

  请确认软件是否曾有过错误启动的历史，且保证配置文件中没有包含重要资料！！！此时，双击软件根目录中的`.bat`文件完成旧配置文件的清理工作后，即可正常初始化软件。

- 其他问题导致的无法启动？

  在软件根目录中打开终端（PowerShell），并执行以下命令：

  ```bash
  .\jre\bin\java.exe --module-path ".\jfx-sdk\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics -jar .\CandiceSta-1.0.0-beta.jar
  ```

  将打印的结果提交至`issues`中，开发者会针对情况进行错误排查。

## 实例

详情见[软件使用说明书](.Doc/documents/CandiceSta-v1.0.0-beta.pdf)。

## 贡献指南

### 软件开发指向

软件在设计之初就是打算每日定时全自动从内网清卫页面爬取相关数据并录入数据库，但鉴于开发者处于外网，无法实时获取内网`html`等清单文件，至使软件成为手动录入的半自动统计工具。

### 加入我

- 软件开发者：

- 版本测试者：

- 前端设计者：

- 清单文件提供者：

  处于校内，且能够接触到清单文件即可。

## 许可证信息

软件使用[MIT许可证](LICENSE)，欢迎对本项目进行分发与指正。

## 维护者信息

- ***`coffeebean`***

  GitHub: `@beanc904`

  Email: 

  - beanc904@163.com(墙内)
  - beanc904@gmail.com(墙外)

  WeChat: `beanc904`

## 鸣谢

### 依赖库

- `org.openjfx`:

  - javafx-controls
  - javafx-fxml

  *注*: 前端UI渲染核心组件

- `org.junit.juniter`:

  - junit-juniter-api
  - junit-jupiter-engine

- `io.github.mkpaz`:

  - atlantafx-base

  *注*: 主题UI接口

- `org.apache.commons`:

  - commons-lang3

- `commons-logging`:

  - commons-logging

- `org.hsqldb`:

  - hsqldb

- `com.healthmarketscience.jackcess`:

  - jackcess
  - jackcess-encrypt

- `net.sf.ucanaccess`:

  - ucanaccess

  *注*: 数据库核心接口

### 其他参与测试

- `@bow`:

  参与开发版本软件后期的测试工作(_此人工作态度极不认真_)，哼。

## 构建状态

软件主要使用`javafx`依赖库，由`fxml`完成前端设计与渲染，`Java21`进行后端开发。构建思路为，以系统环境为准，构建编译出软件工件并完成初步工件的独立运行测试；此后，从系统环境中提取出一个最小的定制`JRE`环境，最后完成软件包的整合。

### `pom.xml`的工件构建核心参数

```xml
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <source>21</source>
          <target>21</target>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.4</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <filters>
                <filter>
                  <artifact>*:*</artifact>
                  <excludes>
                    <exclude>META-INF/*.SF</exclude>
                    <exclude>META-INF/*.DSA</exclude>
                    <exclude>META-INF/*.RSA</exclude>
                  </excludes>
                </filter>
              </filters>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <mainClass>com.beanc.candicesta.MainApplication</mainClass>
                </transformer>
              </transformers>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

***注***：因为使用的依赖库包含签名，所以需要在构建工件时，需要在`META-INF`中对`*.SF`、`*.DSA`、`*.RSA`签名文件进行过滤，否则工件在运行时会存在签名报错问题。

## 版本信息

- 当前版本

  `1.0.0-beta`:
  - **修复**:
    1. 修复`新建班级数据库`处的缓存区不稳定现象
  - **新功能**:
    1. 完善日志导出功能
    2. 添加统计结果导出功能

- 上一版本

  `1.0.0-alpha`: 完成软件的初步开发
