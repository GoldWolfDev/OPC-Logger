USE [TM]
GO

/****** Object:  Table [dbo].[OIS]    Script Date: 08/07/2016 09:59:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[OIS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[DateImport] [datetime] NULL,
	[EventType] [int] NULL,
	[ObjID] [varchar](255) NULL,
	[ObjType] [int] NULL,
	[PropType] [int] NULL,
	[PropValue] [varchar](255) NULL,
	[TimeStamp] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

