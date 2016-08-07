USE [TM_Object]
GO

/****** Object:  Table [dbo].[OPCServer]    Script Date: 08/07/2016 10:00:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[OPCServer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ServerStatus] [int] NULL,
	[clientNameForOPC] [varchar](255) NULL,
	[NameGroup] [varchar](255) NULL,
	[opcServer] [varchar](255) NULL,
	[serverName] [varchar](255) NULL,
	[TimeQuestian] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

