USE [master]
GO
/****** Object:  Database [JarvisDB]    Script Date: 13/07/2018 21:58:07 ******/
CREATE DATABASE [JarvisDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'JarvisDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\JarvisDB.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'JarvisDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\JarvisDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [JarvisDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JarvisDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JarvisDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JarvisDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JarvisDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JarvisDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JarvisDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [JarvisDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [JarvisDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JarvisDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JarvisDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JarvisDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JarvisDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JarvisDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JarvisDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JarvisDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JarvisDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [JarvisDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JarvisDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JarvisDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JarvisDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JarvisDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JarvisDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JarvisDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JarvisDB] SET RECOVERY FULL 
GO
ALTER DATABASE [JarvisDB] SET  MULTI_USER 
GO
ALTER DATABASE [JarvisDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JarvisDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JarvisDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JarvisDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [JarvisDB] SET DELAYED_DURABILITY = DISABLED 
GO
USE [JarvisDB]
GO
/****** Object:  Table [dbo].[Marks]    Script Date: 13/07/2018 21:58:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Marks](
	[markCode] [varchar](50) NOT NULL,
	[markName] [nvarchar](50) NOT NULL,
	[armorType] [nvarchar](50) NOT NULL,
	[description] [nvarchar](250) NULL,
 CONSTRAINT [PK_Marks] PRIMARY KEY CLUSTERED 
(
	[markCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MissionRefs]    Script Date: 13/07/2018 21:58:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MissionRefs](
	[missionCode] [varchar](50) NOT NULL,
	[avengerUsername] [varchar](50) NOT NULL,
	[markCode] [varchar](50) NULL,
 CONSTRAINT [PK_MissionRefs] PRIMARY KEY CLUSTERED 
(
	[missionCode] ASC,
	[avengerUsername] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Missions]    Script Date: 13/07/2018 21:58:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Missions](
	[missionCode] [varchar](50) NOT NULL,
	[missionName] [nvarchar](50) NOT NULL,
	[location] [varchar](50) NOT NULL,
	[description] [nvarchar](250) NULL,
	[state] [varchar](10) NOT NULL,
	[startDate] [date] NOT NULL,
	[finishDate] [date] NULL,
 CONSTRAINT [PK_Avengers] PRIMARY KEY CLUSTERED 
(
	[missionCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 13/07/2018 21:58:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[alias] [nvarchar](50) NOT NULL,
	[realname] [nvarchar](50) NOT NULL,
	[description] [nvarchar](250) NULL,
 CONSTRAINT [PK_Avengers_1] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK I', N'Mark 1', N'No armor type', N'The Mark I (Mark 1), was the first Iron Man suit built and created by Tony Stark. It was built in the initial events of the live-action film, Iron Man. The suit was later used as a model for Obadiah Stane''s Iron Monger.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK II', N'Mark 2', N'No armor type', N'The Mark II (Mark 2), was the second Iron Man Armor designed and created by Tony Stark and is the successor to the original Mark I armor.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK III', N'Mark 3', N'No armor type', N'The Mark III (Mark 3) was the third Iron Man Armor designed and created by Tony Stark and is the successor to the Mark II.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK IV', N'Mark 4', N'No armor type', N'The Mark IV (Mark 4) was the fourth Iron Man Armor designed and created by Tony Stark as the successor to older Mark III model.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK IX', N'Mark 9', N'No armor type', N'The Mark 9 (Mark IX) is a Fully-Loaded Advanced Flight Prototype, and was the ninth suit created by Tony Stark, to surpass the Mark VIII, sometime after the events of The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK V', N'Suitcase Armor', N'Emergency Suit', N'The Mark V (Mark 5), also known by its names as the "Football" and Suitcase Armor, is an Emergency Suit, and was the fifth Iron Man Armor designed and created by Tony Stark.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK VI', N'Mark 6', N'All Purpose Suit', N'The Mark VI (Mark 6) is an All-Purpose Suit, and was the sixth suit built and created by Tony Stark after he found the solution to the Palladium core''s poisoning, which was the newly improved Arc Reactor, in the movie Iron Man 2.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK VII', N'Mark 7', N'Fully Loaded Suit', N'The Mark 7 (Mark VII), is a Fully Loaded Rapid Deployment Suit, and was the seventh suit created by Tony Stark, to surpass the Mark VI, sometime between the events of Iron Man 2 and The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK VIII', N'Mark 8', N'No armor type', N'The Mark VIII (8), is a Fully Loaded Suit Upgrade, and was the eighth suit created and built by Tony Stark, to surpass the Mark VII, sometime after the events of The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK X', N'Mark 10', N'No armor type', N'The Mark X (10), was the first Advanced Iron Man Suit, and was the tenth suit created and built by Tony Stark, after the successful completion of the Mark IX, sometime after the events of The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XI', N'Mark 11', N'No armor type', N'The Mark XI (11), was the second Advanced Iron Man Suit, and was the eleventh suit created and built by Tony Stark, after the completion and successful tests on the Mark X.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XII', N'Mark 12', N'No armor type', NULL)
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XIII', N'Mark 13', N'No armor type', NULL)
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XIV', N'Mark 14', N'No armor type', NULL)
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XIX', N'Tiger', N'High Velocity Suit', N'The Mark 19 (XIX), also known by its name as "Tiger", is a prototype High-Velocity Suit, and was one of the many newly built suits created by Tony Stark , sometime after the events of The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XL', N'Shotgun', N'Hypervelocity Traveling Suit', N'The Mark 40 (Mark XL), also known as "Shotgun", is a Hyper Velocity Traveling Suit and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XLI', N'Bones', N'Skeleton Suit', N'The Mark 41 (Mark XLI), also known by its name as "Bones", is a Skeleton Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XLII', N'The Prodigal Son', N'Prehensile Suit', N'The Mark 42 (XLII), known by its code name "Extremis" or the “Prodigal Son” is an Autonomous Prehensile Propulsion Suit Prototype was the forty second armor, as well as one of the many newly built suits.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XV', N'Sneaky', N'Stealth Suit', NULL)
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XVI', N'Nightclub', N'Black Stealth Suit', N'The Mark 16 (Mark XVI), also known by its name as "Nightclub", is a Black Stealth Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XVII', N'Heartbreaker', N'Artillery Level RT Suit', N'The Mark 17 (Mark XVII), famously known by its name as the "Heartbreaker", is an Artillery Level RT Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XVIII', N'Cassanova', N'Stealth Artillery Level RT Suit', N'The Mark 18 (Mark XVIII), also known by its codename as "Casanova", is a Stealth Artillery Level RT Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XX', N'Python', N'Long Distance Suit', N'The Mark 20 (Mark XX), also known by its codename as "Python", is a Fully Loaded Long-Distance Prototype Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXI', N'Midas', N'High Altitude Suit', N'The Mark 21 (XXI), also known as "Midas", is a Fully Loaded High-Altitude Suit, and was one of the many newly-built suits created by Tony Stark sometime after the events of The Avengers.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXII', N'Hotrod', N'War Machine 2 Prototype Suit', N'The Mark 22 (Mark XXII), also known by its name as "Hotrod", is a War Machine 2.0 Prototype Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXIII', N'Shades', N'Extreme Heat Suit', N'The Mark 23 (Mark XXIII), also known by its name as "Shades", is an Extreme Heat Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXIV', N'The Tank', N'Heavy Combat Suit', N'The Mark 24 (Mark XXIV), also known by its name as "Tank", is a Heavy Combat Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXIX', N'Fiddler', N' Nimble Construction Suit', N'The Mark 29 (Mark XXIX), also known by its code-name as "Fiddler", is a Lightweight Construction Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXV', N'Striker', N'Heavy Construction Suit', N'The Mark 25 (Mark XXV), also known by its code-name as "Striker" or "Thumper", is a Heavy Construction Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXVI', N'Gamma', N'Heavy Construction Suit Upgrade', N'The Mark 26 (Mark XXVI), also known by its codename as "Gamma", is a Gamma Radiation Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXVII', N'Disco', N' Chameleon Suit', N'The Mark 27 (Mark XXVII), also known by its name as "Disco", is a Chameleon Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXVIII', N'The Jack', N'Radiation Zone Suit', N'The Mark 28 (Mark XXVIII), also known by its name as "Jack", is a Radiation-Zone Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXX', N'Blue Steel', N'Silver Centurion Suit Upgrade', N'The Mark 30 (Mark XXX), also known by its codename as "Blue Steel", is a Silver Centurion Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXI', N'Piston', N'High Velocity Centurion Suit', N'The Mark 31 (Mark XXXI), also known by its name as "Piston", is a Fully Loaded High-Velocity Centurion Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXII', N'Romeo', N'Enhanced RT Suit', N'The Mark 32 (Mark XXXII), also known by its codename as "Romeo", is an Enhanced RT Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXIII', N'Silver Centurion', N'Enhanced Energy Suit', N'The Mark 33 (Mark XXXIII), also known by its code name as the "Silver Centurion", is an Enhanced Energy Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXIV', N'Southpaw', N'Disaster Rescue Suit Prototype', NULL)
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXIX', N'Gemini Starboost', N'Sub Orbital Suit', N'The Mark 39 (Mark XXXIX), also known by its names as "Gemini" and "Starboost", is a Sub-Orbital Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXV', N' Red Snapper', N'Disaster Rescue Suit', N'The Mark 35 (Mark XXXV), also known by its codename as "Red Snapper", is a Disaster Rescue Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXVI', N'Peacemaker', N'Riot Control Suit', N'The Mark 36 (Mark XXXVI), also known by its codename as "Peacemaker", is a Riot Control Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXVII', N'Hammerhead', N'Deep Seas Suit', N'The Mark 37 (Mark XXXVII), also known by its codename as "Hammerhead", is a Deep Sea Suit, and was one of several new Iron Man Armors created by Tony Stark as part of the Iron Legion.')
INSERT [dbo].[Marks] ([markCode], [markName], [armorType], [description]) VALUES (N'MARK XXXVIII', N'The Igor', N'Heavy Lifting Suit', N'The Mark 38 (Mark XXXVIII), also known by its name as "Igor", is a Heavy Lifting Suit, and was one of several new Iron Man Armors created by Tony Stark as a part of the Iron Legion.')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'ANTMN-1', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'ANTMN-1', N'falcon', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'ANTMN-1', N'hankpym', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'ANTMN-2', N'hankpym', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'ANTMN-2', N'thewasp', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'BLKPT-1', N'blackpanther', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-1', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-2', N'blackwidow', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-2', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-2', N'falcon', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-2', N'scarletwitch', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'blackpanther', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'falcon', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'hankpym', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'hawkeye', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'stark', N'MARK XL')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'CAPTN-3', N'vision', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'IRONM-1', N'stark', N'MARK I')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'IRONM-2', N'stark', N'MARK II')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'IRONM-3', N'stark', N'MARK IX')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'blackwidow', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'hawkeye', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'stark', N'MARK X')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'thehulk', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-1', N'thethor', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'blackwidow', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'falcon', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'hawkeye', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'quicksilver', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'scarletwitch', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'stark', N'MARK XIX')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'thehulk', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'thethor', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-2', N'vision', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'blackpanther', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'blackwidow', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'falcon', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'scarletwitch', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'stark', N'MARK XXXVIII')
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'thehulk', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'thethor', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TAVNG-3', N'vision', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TICHK-1', N'thehulk', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TTHOR-1', N'thethor', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TTHOR-2', N'captainamerica', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TTHOR-2', N'thethor', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TTHOR-3', N'thehulk', NULL)
INSERT [dbo].[MissionRefs] ([missionCode], [avengerUsername], [markCode]) VALUES (N'TTHOR-3', N'thethor', NULL)
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'ANTMN-1', N'Ant Man', N'United States of America', N'Forced out of his own company by former protÃ©gÃ© Darren Cross, Dr. Hank Pym (Michael Douglas) recruits the talents of Scott Lang (Paul Rudd), a master thief just released from prison.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'ANTMN-2', N'Ant Man and the Wasp', N'United States of America', N'Scott Lang is grappling with the consequences of his choices as both a superhero and a father.', N'RUNNING', CAST(N'2018-07-13' AS Date), NULL)
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'BLKPT-1', N'Black Panther', N'Kenya', N'After the death of his father, T''Challa returns home to the African nation of Wakanda to take his rightful place as king. When a powerful enemy suddenly reappears.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'CAPTN-1', N'Captain America The First Avenger', N'United Kingdom', N'It is 1941 and the world is in the throes of war. Steve Rogers (Chris Evans) wants to do his part and join America''s armed forces, but the military rejects him because of his small stature.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'CAPTN-2', N'Captain America The Winter Soldier', N'United States of America', N'After the cataclysmic events in New York with his fellow Avengers, Steve Rogers, aka Captain America (Chris Evans), lives in the nation''s capital as he tries to adjust to modern times.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'CAPTN-3', N'Captain America Civil War', N'Russian Federation', N'Political pressure mounts to install a system of accountability when the actions of the Avengers lead to collateral damage. The new status quo deeply divides members of the team.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'IRONM-1', N'Iron Man 1', N'United States of America', N'A billionaire industrialist and genius inventor, Tony Stark (Robert Downey Jr.), is conducting weapons tests overseas, but terrorists kidnap him to force him to build a devastating weapon', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'IRONM-2', N'Iron Man 2', N'France', N'With the world now aware that he is Iron Man, billionaire inventor Tony Stark (Robert Downey Jr.) faces pressure from all sides to share his technology with the military.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'IRONM-3', N'Iron Man 3', N'United States of America', N'Plagued with worry and insomnia since saving New York from destruction, Tony Stark (Robert Downey Jr.), now, is more dependent on the suits that give him his Iron Man person.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TAVNG-1', N'The Avengers', N'United States of America', N'When Thor''s evil brother, Loki (Tom Hiddleston), gains access to the unlimited power of the energy cube called the Tesseract, Nick Fury (Samuel L. Jackson), director of S.H.I.E.L.D.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TAVNG-2', N'Avengers Age of Ultron', N'Slovakia', N'When Tony Stark (Robert Downey Jr.) jump-starts a dormant peacekeeping program, things go terribly awry, forcing him, Thor (Chris Hemsworth).', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TAVNG-3', N'Avengers Infinity War', N'Kenya', N'Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos.', N'RUNNING', CAST(N'2018-07-13' AS Date), NULL)
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TICHK-1', N'The Incredible Hulk', N'Canada', N'Scientist Bruce Banner (Edward Norton) desperately seeks a cure for the gamma radiation that contaminated his cells and turned him into The Hulk.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TTHOR-1', N'Thor', N'Mexico', N'As the son of Odin (Anthony Hopkins), king of the Norse gods, Thor (Chris Hemsworth) will soon inherit the throne of Asgard from his aging father.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TTHOR-2', N'Thor The Dark World', N'United Kingdom', N'In ancient times, the gods of Asgard fought and won a war against an evil race known as the Dark Elves.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Missions] ([missionCode], [missionName], [location], [description], [state], [startDate], [finishDate]) VALUES (N'TTHOR-3', N'Thor Ragnarok', N'United States of America', N'Imprisoned on the other side of the universe, the mighty Thor finds himself in a deadly gladiatorial contest that pits him against the Hulk, his former ally and fellow Avenger.', N'DONE', CAST(N'2018-07-13' AS Date), CAST(N'2018-07-13' AS Date))
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'blackpanther', N'Blackpanther123!@#', N'Black Panther', N'TChalla', N'I am TChalla, son of TChaka')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'blackwidow', N'Blackwidow123!@#', N'Black Widow', N'Natasha Romanova', N'Natasha Romanova, known by many aliases, is an expert spy, athlete, and assassin. Trained at a young age by the KGB''s infamous Red Room Academy, the Black Widow was formerly an enemy to the Avengers.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'captainamerica', N'Captainamerica123!@#', N'Captain America', N'Steven Rogers', N'Captain America had mastered the martial arts of American-style boxing and judo, and had combined these disciplines with his own unique hand-to-hand style of combat.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'captainmarvel', N'Captainmarvel123!@#', N'Captain Marvel', N'Carol Danvers', NULL)
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'falcon', N'Falcon123!@#', N'Falcon', N'Samuel Thomas Wilson', N'Excellent trainer of birds, and has been highly trained in gymnastics and hand-to-hand combat by Captain America (Steve Rogers), Captain America.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'hankpym', N'Hankpym123!@#', N'Ant Man', N'Ant Man', N'Pym is a brilliant scientist in both the feilds of robotics/cybernetics and biochemistry. During his carreer, he created robot Ultron, explored and repaired the Vision often, and created much of the Avengers'' equipment.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'hawkeye', N'Hawkeye123!@#', N'Hawkeye', N'Unknown', NULL)
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'jarvis', N'Jarvis123!@#', N'JARVIS', N'Jarvis', N'Jarvis rule the world')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'lukecage', N'Lukecage123!@#', N'Luke Cage', N'Luke Cage', NULL)
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'quicksilver', N'Quicksilver123!@#', N'Quick Silver', N'Pietro Django Maximoff', N'Quicksilver was trained by Captain America in hand-to-hand combat.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'scarletwitch', N'Scarletwitch123!@#', N'Scarlet Witch', N'Wanda Maximoff', N'The Scarlet Witch can tap into mystic energy for reality-altering effects; this power was formerly limited to the creation of "hex-spheres" of reality-disrupting quasi-psionic force to cause molecular disturbances in a target.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'spiderwoman', N'Spiderwoman123!@#', N'Spider Woman', N'Jessica Drew', N'Jessica is an experienced fighter and excellent investigator.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'stark', N'Stark123!@#', N'Iron Man', N'Tony Starkaaa', N'Tony has a genius level intellect that allows him to invent a wide range of sophisticated devices, specializing in advanced weapons and armor. He possesses a keen business mind.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'thehulk', N'Thehulk123!@#', N'The Hulk', N'Robert Bruce Banner', N'r. Bruce Banner is a genius in nuclear physics, possessing a mind so brilliant that it cannot be measured on any known intelligence test.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'thethor', N'Thethor123!@#', N'The Thor', N'Thor Odinson', N'Thor is trained in the arts of war, being a superbly skilled warrior, highly proficient in hand-to-hand combat, swordsmanship and hammer throwing.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'thewasp', N'Thewasp123!@#', N'The Wasp', N'Janet Van Dyne', N'Combat and leader skills.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'vision', N'Vision123!@#', N'Vision', N'Vision', N'The metal monstrosity called Ultron created the synthetic humanoid known as the Vision from the remains of the original android Human Torch of the 1940s to serve as a vehicle of vengeance.')
INSERT [dbo].[Users] ([username], [password], [alias], [realname], [description]) VALUES (N'wonderman', N'Wonderman123!@#', N'Wonder Man', N'Simon Williams', N'Superhuman levels of strength and invulnerability. Due to exposure to ionic energy, his body has "fully metamorphosed" into "unspecified superhuman flesh-like substance nourished by ionic energy.”')
ALTER TABLE [dbo].[MissionRefs]  WITH CHECK ADD  CONSTRAINT [FK_MissionRefs_Avengers] FOREIGN KEY([avengerUsername])
REFERENCES [dbo].[Users] ([username])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MissionRefs] CHECK CONSTRAINT [FK_MissionRefs_Avengers]
GO
ALTER TABLE [dbo].[MissionRefs]  WITH CHECK ADD  CONSTRAINT [FK_MissionRefs_Marks] FOREIGN KEY([markCode])
REFERENCES [dbo].[Marks] ([markCode])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MissionRefs] CHECK CONSTRAINT [FK_MissionRefs_Marks]
GO
ALTER TABLE [dbo].[MissionRefs]  WITH CHECK ADD  CONSTRAINT [FK_MissionRefs_Missions] FOREIGN KEY([missionCode])
REFERENCES [dbo].[Missions] ([missionCode])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MissionRefs] CHECK CONSTRAINT [FK_MissionRefs_Missions]
GO
USE [master]
GO
ALTER DATABASE [JarvisDB] SET  READ_WRITE 
GO
