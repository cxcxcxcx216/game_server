@echo off
echo path : %~dp0
dir    %~dp0\*.proto /B > proto.list       

for  /f  %%a  in  (proto.list)  do (
    echo %%a building...
    protoc --csharp_out=J:\Game\Game\proto %%a
)

del proto.list
pause